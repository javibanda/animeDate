package org.javibanda.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.mapper.ProfileMapper;
import org.javibanda.model.dto.ProfileRequest;
import org.javibanda.model.dto.ProfileResponseShort;
import org.javibanda.model.entity.user.Profile;
import org.javibanda.model.entity.user.ShortProfile;
import org.javibanda.repository.ProfileRepository;
import org.javibanda.service.AnimeService;
import org.javibanda.service.CharacterService;
import org.javibanda.service.ProfileService;
import org.javibanda.service.UserService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository repository;
    private final CharacterService characterService;
    private final UserService userService;
    private final AnimeService animeService;

    public void save(ProfileRequest request, UUID userId) throws ParseException {
        val character = characterService.getByName(request.getFavoriteCharacter());
        val waifu = characterService.getByName(request.getWaifu());
        val profile = ProfileMapper.toEntity(character, waifu, userId, request);
        repository.save(profile);
    }

    public ProfileResponseShort getProfile(UUID profileId){
        Profile profile = repository.findProfileById(profileId);
        if (profile == null){
            return null;
        }
        String userName = userService.getName(profile.getUserId());
        List<String> favoriteAnimes = animeService.getFavoriteAnimes(profileId);
        return ProfileMapper.toDTO(profile, favoriteAnimes, userName);
    }

    public ShortProfile getShortProfile(UUID profileId){
        return repository.findShortProfileById(profileId);
    }

}
