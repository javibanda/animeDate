package org.javibanda.model.entity.anime;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "anime_profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimeProfile {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "id_profile", nullable = false)
    private UUID profile;
    @Column(name = "anime", nullable = false, length = 200)
    private String anime;
}