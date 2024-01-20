package org.javibanda.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.javibanda.model.BaseProfile;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileRequest extends BaseProfile {

    private String favoriteCharacter;

    private String waifu;

    private String birthDate;
}
