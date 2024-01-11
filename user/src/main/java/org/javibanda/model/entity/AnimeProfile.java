package org.javibanda.model.entity;
import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "anime_profile")
@Data
public class AnimeProfile {

    @Id
    @Column(name = "id", nullable = false)
    private java.util.UUID id;

    @Column(name = "id_profile", nullable = false)
    private String profile;

    @Column(name = "anime", nullable = false, length = 200)
    private String anime;
}