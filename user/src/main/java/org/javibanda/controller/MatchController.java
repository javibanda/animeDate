package org.javibanda.controller;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.entity.match.Match;
import org.javibanda.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/match")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/all")
    public ResponseEntity<List<Match>> getFavoriteAnimes() {
        return ResponseEntity.ok(matchService.getAll());
    }
}
