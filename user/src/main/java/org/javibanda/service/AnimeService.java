package org.javibanda.service;

import org.javibanda.model.entity.anime.Anime;

import java.util.List;
import java.util.UUID;

public interface AnimeService {

    void save(String animeName);

    Anime get(String animeName);

    List<String> getAll();

    List<String> getFavoriteAnimes(UUID profileId);

    void saveFavoriteAnime(UUID profileId, List<String> animes);

}
