package org.javibanda.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "anime_profile")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimeProfile {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "id_profile", nullable = false)
    private UUID profile;
    @Column(name = "anime", nullable = false, length = 200)
    private String anime;
}