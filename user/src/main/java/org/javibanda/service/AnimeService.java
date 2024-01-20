package org.javibanda.service;
import lombok.AllArgsConstructor;
import lombok.val;
import org.javibanda.mapper.AnimeMapper;
import org.javibanda.model.entity.anime.Anime;
import org.javibanda.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnimeService {
    private final AnimeRepository repository;
    private final AnimeProfileService animeProfileService;

    public void save(String animeName){
        val anime = AnimeMapper.toEntity(animeName);
        repository.save(anime);
    }

    public Anime get(String animeName){
        return repository.findByName(animeName);
    }

    public List<String> getAll(){
        return repository.findAllAnimeName();
    }

    public List<String> getFavoriteAnimes(UUID profileId){
        return repository.findAnimeByProfile(profileId);
    }

    public void saveFavoriteAnime(UUID profileId, List<String> animes){
        animeProfileService.save(profileId, animes);
    }

}
