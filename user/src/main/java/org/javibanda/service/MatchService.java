package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.entity.match.Match;
import org.javibanda.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;

    public List<Match> getAll(){
        return repository.findAll();
    }
}
