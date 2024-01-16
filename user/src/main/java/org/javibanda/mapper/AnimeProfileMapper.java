package org.javibanda.mapper;

import org.javibanda.model.entity.anime.AnimeProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnimeProfileMapper {
    public static List<AnimeProfile> toEntity(UUID profileId, List<String> animes){
        List<AnimeProfile> entity = new ArrayList<>();
        for (String anime: animes){
            entity.add(new AnimeProfile(UUID.randomUUID(),profileId, anime));
        }
        return entity;
    }
}


