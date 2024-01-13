package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.mapper.AnimeProfileMapper;
import org.javibanda.repository.AnimeProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimeProfileService {
    private final AnimeProfileRepository repository;

    public void save(UUID profileId, List<String> animes){
        repository.saveAll(AnimeProfileMapper.toEntity(profileId, animes));
    }

}
