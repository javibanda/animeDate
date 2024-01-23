package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.dto.ClaimDTO;
import org.javibanda.model.dto.MatchRequest;
import org.javibanda.model.entity.user.ShortProfile;
import org.javibanda.service.MatchService;
import org.javibanda.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createMatch(@RequestHeader("Authorization") String token,
                                              @RequestBody MatchRequest request){
        matchService.createMatch(yourProfileId(token), request.getProfileId(), request.getMatch());
        return ResponseEntity.ok("Ok");
    }

    @GetMapping
    public ResponseEntity<List<ShortProfile>> getMatches(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(matchService.getMatches(yourProfileId(token)));
    }

    @GetMapping("/forMatches")
    public ResponseEntity<List<ShortProfile>> getProfilesForMatches(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(matchService.getProfilesForMatches(getClaimDto(token).getProfileId()));
    }

    @GetMapping("/countWhoLikedMe")
    public ResponseEntity<Integer> getCountProfilesWhoLikedMe(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(matchService.getCountProfilesWhoLikedMe(yourProfileId(token)));
    }

    @GetMapping("/whoMatchedMe")
    public ResponseEntity<List<ShortProfile>> getProfilesWhoMatchedMe(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(matchService.getProfilesWhoLikedMe(yourProfileId(token)));
    }

    private ClaimDTO getClaimDto(String token){
        return userService.getUserFromToken(token);
    }

    private UUID yourProfileId(String token){
        return getClaimDto(token).getProfileId();
    }
}
