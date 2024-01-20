package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.dto.ProfileRequest;
import org.javibanda.model.dto.ProfileResponseShort;
import org.javibanda.service.ProfileService;
import org.javibanda.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping(value = "/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> save(@RequestHeader("Authorization") String token,
                                       @RequestBody ProfileRequest request) {

        profileService.save(request, getClaimDto(token).getId());
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/short")
    public ResponseEntity<ProfileResponseShort> getShortProfile(@RequestParam UUID profileId) {
        val profileResponse = profileService.getShortProfile(profileId);
        if (profileDontExist(profileResponse)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(profileResponse);
    }

    private ClaimDTO getClaimDto(String token){
        return userService.getUserFromToken(token);
    }

    private boolean profileDontExist(ProfileResponseShort profileResponse){
        return profileResponse == null;
    }
}
