package org.javibanda.service.impl;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.entity.anime.AnimeCharacter;
import org.javibanda.repository.CharacterRepository;
import org.javibanda.service.CharacterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository repository;

    @Override
    public void save(AnimeCharacter character){
        repository.save(character);
    }

    @Override
    public boolean characterExist(String characterName){
        return repository.existsById(characterName);
    }

    @Override
    public AnimeCharacter getByName(String name){
        return repository.findByName(name);
    }

}
