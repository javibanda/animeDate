package org.javibanda.service;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.javibanda.mapper.ProfileMapper;
import org.javibanda.model.dto.ProfileRequest;
import org.javibanda.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;
    private final CharacterService characterService;

    public void save(ProfileRequest request, UUID userId){
        val character = characterService.getByName(request.getFavoriteCharacter());
        val waifu = characterService.getByName(request.getWaifu());
        val profile = ProfileMapper.toEntity(character, waifu, userId, request);
        repository.save(profile);
    }
}
