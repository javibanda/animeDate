package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.model.dto.*;
import org.javibanda.service.ProfileService;
import org.javibanda.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.UUID;


@RestController
@RequestMapping(value = "/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<StringResponse> save(@RequestHeader("Authorization") String token,
                                               @RequestBody ProfileRequest request) throws ParseException {
        //TODO CONTROL SI USUARIO YA ESTA REGISTRADO

        profileService.save(request, getClaimDto(token).getId());
        return ResponseEntity.ok(new StringResponse("OK"));
    }


    @GetMapping("/existProfile")
    public ResponseEntity<BooleanResponse> existProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(new BooleanResponse(!profileDontExist(profileService.getProfile(yourProfileId(token)))));
    }

    @GetMapping("/short")
    public ResponseEntity<ProfileResponseShort> getShortProfile(@RequestParam UUID profileId) {
        val profileResponse = profileService.getProfile(profileId);
        if (profileDontExist(profileResponse)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(profileResponse);
    }

    private ClaimDTO getClaimDto(String token) {
        return userService.getUserFromToken(token);
    }

    private boolean profileDontExist(ProfileResponseShort profileResponse) {
        return profileResponse == null;
    }

    private UUID yourProfileId(String token) {
        return getClaimDto(token).getProfileId();
    }
}
