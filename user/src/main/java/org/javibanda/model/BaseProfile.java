package org.javibanda.model;

import lombok.Data;
import org.javibanda.model.enums.Sex;
import org.javibanda.model.enums.SexualOrientation;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
public class BaseProfile {
    @Id
    @Column(name = "id", nullable = false)
    protected UUID id;
    @Column(name = "sex", nullable = false, length = 20)
    protected Sex sex;
    @Column(name = "favorite_anime")
    private String favoriteAnime;
    @Column(name = "sexual_orientation", nullable = false, length = 200)
    protected SexualOrientation sexualOrientation;
    @Column(name = "waifu", nullable = false, length = 200)
    protected String waifu;
    @Column(name = "bio", columnDefinition = "text")
    protected String bio;
    @Column(name = "what_search", nullable = false, length = 200)
    protected String whatSearch;
    @Column(name = "user_id", nullable = false, unique = true)
    protected UUID userId;
    @Column(name = "is_cosplayer", nullable = false)
    protected boolean isCosplayer = true;
}
