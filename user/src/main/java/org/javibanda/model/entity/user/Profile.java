package org.javibanda.model.entity.user;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.javibanda.model.BaseProfile;
import org.javibanda.model.entity.anime.AnimeCharacter;

import javax.persistence.*;
import java.util.Date;
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
    @Column(name = "id", columnDefinition = "uuid", nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "favorite_character", nullable = false)
    private AnimeCharacter favoriteCharacter;

    private String waifu;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

}
