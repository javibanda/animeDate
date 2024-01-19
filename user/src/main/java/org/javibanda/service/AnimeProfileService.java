package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.mapper.AnimeProfileMapper;
import org.javibanda.model.entity.anime.AnimeProfile;
import org.javibanda.repository.AnimeProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimeProfileService {
    private final AnimeProfileRepository repository;

    public void save(UUID profileId, List<String> animes){
        val animeProfiles = AnimeProfileMapper.toEntity(profileId, animes);
        for (AnimeProfile animeProfile: animeProfiles){
            if (animeProfileNotExist(animeProfile)){
                repository.save(animeProfile);
            }
        }
    }

    private boolean animeProfileNotExist(AnimeProfile animeProfile){
        return repository.getAnimeProfileByAnimeAndProfile(animeProfile.getAnime(), animeProfile.getProfile()).isEmpty();
    }

}
