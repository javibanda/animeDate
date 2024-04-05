package org.javibanda.service;

import org.javibanda.model.entity.anime.AnimeCharacter;

public interface CharacterService {

    void save(AnimeCharacter character);

    boolean characterExist(String characterName);

    AnimeCharacter getByName(String name);

}
