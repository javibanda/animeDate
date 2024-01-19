package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.dto.ProfileRequest;
import org.javibanda.service.ProfileService;
import org.javibanda.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    private ClaimDTO getClaimDto(String token){
        return userService.getUserFromToken(token);
    }
}
