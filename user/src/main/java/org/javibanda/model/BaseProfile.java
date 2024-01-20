package org.javibanda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.javibanda.model.enums.Sex;
import org.javibanda.model.enums.SexualOrientation;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseProfile {
    @Column(name = "sex", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    protected Sex sex;
    @Column(name = "favorite_anime")
    protected String favoriteAnime;
    @Column(name = "sexual_orientation", nullable = false, length = 200)
    @Enumerated(EnumType.STRING)
    protected SexualOrientation sexualOrientation;
    @Column(name = "bio", columnDefinition = "text")
    protected String bio;
    @Column(name = "what_search", nullable = false, length = 200)
    protected String whatSearch;
    @Column(name = "user_id", nullable = false, unique = true)
    protected UUID userId;
    @Column(name = "is_cosplayer", nullable = false)
    protected boolean isCosplayer = true;
    @Column(name = "name")
    private String name;
}
