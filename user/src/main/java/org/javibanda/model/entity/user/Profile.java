package org.javibanda.model.entity.user;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.javibanda.model.BaseProfile;
import org.javibanda.model.entity.anime.AnimeCharacter;

import javax.persistence.*;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Profile extends BaseProfile {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "favorite_character", nullable = false)
    private AnimeCharacter favoriteCharacter;
    @ManyToOne
    @JoinColumn(name = "waifu", nullable = false)
    private AnimeCharacter waifu;

}
