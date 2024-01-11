package org.javibanda.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.javibanda.model.BaseProfile;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "profile")
@Data
public class Profile extends BaseProfile {
    @ManyToOne
    @JoinColumn(name = "favorite_character", nullable = false)
    private AnimeCharacter favoriteCharacter;

}
