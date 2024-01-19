package org.javibanda.mapper;

import org.javibanda.model.dto.ProfileRequest;
import org.javibanda.model.entity.anime.AnimeCharacter;
import org.javibanda.model.entity.user.Profile;

import java.util.UUID;

public class ProfileMapper {

    public static Profile toEntity(AnimeCharacter favoriteCharacter,
                                   AnimeCharacter waifu,
                                   UUID userId,
                                   ProfileRequest request){
        return Profile.builder()
                .id(UUID.randomUUID())
                .favoriteCharacter(favoriteCharacter)
                .waifu(waifu)
                .userId(userId)
                .bio(request.getBio())
                .sex(request.getSex())
                .sexualOrientation(request.getSexualOrientation())
                .isCosplayer(request.isCosplayer())
                .favoriteAnime(request.getFavoriteAnime())
                .whatSearch(request.getWhatSearch())
                .build();
    }
}
