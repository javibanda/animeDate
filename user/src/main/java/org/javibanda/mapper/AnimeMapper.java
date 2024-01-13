package org.javibanda.mapper;

import org.javibanda.model.entity.Anime;

public class AnimeMapper {
    public static Anime toEntity(String animeName){
        return Anime.builder()
                .name(animeName)
                .build();
    }
}
