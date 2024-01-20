package org.javibanda.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.javibanda.model.BaseProfile;
import org.javibanda.model.entity.anime.AnimeCharacter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include. NON_NULL)
public class ProfileResponseShort extends BaseProfile {
    private int age;
    private String name;
    private AnimeCharacter favoriteCharacter;
    private List<String> favoriteAnimes;

}
