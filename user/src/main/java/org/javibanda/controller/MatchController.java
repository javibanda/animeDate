package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.dto.MatchRequest;
import org.javibanda.model.entity.user.ShortProfile;
import org.javibanda.service.MatchService;
import org.javibanda.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<String> createMatch(@RequestHeader("Authorization") String token,
                                              @RequestBody MatchRequest request){
        val yourProfileId = getClaimDto(token).getProfileId();
        matchService.createMatch(yourProfileId, request.getProfileId(), request.getMatch());
        return ResponseEntity.ok("Ok");
    }

    private ClaimDTO getClaimDto(String token){
        return userService.getUserFromToken(token);
    }
}
