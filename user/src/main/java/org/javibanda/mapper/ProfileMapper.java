package org.javibanda.mapper;

import org.javibanda.model.dto.ProfileRequest;
import org.javibanda.model.dto.ProfileResponseShort;
import org.javibanda.model.entity.anime.AnimeCharacter;
import org.javibanda.model.entity.user.Profile;
import org.javibanda.util.UtilDate;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

public class ProfileMapper {

    public static Profile toEntity(AnimeCharacter favoriteCharacter,
                                   UUID userId,
                                   ProfileRequest request) throws ParseException {
        return Profile.builder()
                .id(UUID.randomUUID())
                .favoriteCharacter(favoriteCharacter)
                .waifu(request.getWaifu())
                .userId(userId)
                .bio(request.getBio())
                .sex(request.getSex())
                .sexualOrientation(request.getSexualOrientation())
                .isCosplayer(request.isCosplayer())
                .favoriteAnime(request.getFavoriteAnime())
                .whatSearch(request.getWhatSearch())
                .birthDate(UtilDate.formateDate(request.getBirthDate()))
                .name(request.getName())
                .build();
    }

    public static ProfileResponseShort toDTO(Profile profile, List<String> favoriteAnimes, String userName){
        return ProfileResponseShort.builder()
                .age(UtilDate.calculateAge(profile.getBirthDate()))
                .favoriteAnimes(favoriteAnimes)
                .favoriteAnime(profile.getFavoriteAnime())
                .favoriteCharacter(profile.getFavoriteCharacter())
                .name(userName)
                .bio(profile.getBio())
                .isCosplayer(profile.isCosplayer())
                .build();
    }
}
