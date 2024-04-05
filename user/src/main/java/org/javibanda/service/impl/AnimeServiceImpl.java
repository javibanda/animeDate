package org.javibanda.service.impl;
import lombok.AllArgsConstructor;
import lombok.val;
import org.javibanda.mapper.AnimeMapper;
import org.javibanda.model.entity.anime.Anime;
import org.javibanda.repository.AnimeRepository;
import org.javibanda.service.AnimeProfileService;
import org.javibanda.service.AnimeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository repository;

    private final AnimeProfileService animeProfileService;

    @Override
    public void save(String animeName){
        val anime = AnimeMapper.toEntity(animeName);
        repository.save(anime);
    }

    @Override
    public Anime get(String animeName){
        return repository.findByName(animeName);
    }

    @Override
    public List<String> getAll(){
        return repository.findAllAnimeName();
    }

    @Override
    public List<String> getFavoriteAnimes(UUID profileId){
        return repository.findAnimeByProfile(profileId);
    }

    @Override
    public void saveFavoriteAnime(UUID profileId, List<String> animes){
        animeProfileService.save(profileId, animes);
    }

}
