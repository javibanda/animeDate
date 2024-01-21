package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.entity.user.ShortProfile;
import org.javibanda.service.MatchService;
import org.javibanda.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final UserService userService;

    @GetMapping("/matchedProfiles")
    public ResponseEntity<List<ShortProfile>> getMatchedProfiles(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(matchService.getMatchedProfiles(getClaimDto(token).getProfileId()));
    }

    private ClaimDTO getClaimDto(String token){
        return userService.getUserFromToken(token);
    }
}
