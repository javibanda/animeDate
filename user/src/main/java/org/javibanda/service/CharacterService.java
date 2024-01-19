package org.javibanda.service;

import lombok.RequiredArgsConstructor;
import org.javibanda.model.entity.anime.AnimeCharacter;
import org.javibanda.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository repository;

    public void save(AnimeCharacter character){
        repository.save(character);
    }

    public boolean characterExist(String characterName){
        return repository.existsById(characterName);
    }

    public AnimeCharacter getByName(String name){
        return repository.findByName(name);
    }

}
