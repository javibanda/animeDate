package org.javibanda.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "anime_character")
@Getter
@Setter
public class AnimeCharacter {
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "url_photo")
    private String urlPhoto;
    @ManyToOne
    @JoinColumn(name = "anime", nullable = false)
    private Anime anime;
    @Column(name = "sex", nullable = false, length = 20)
    private String sex = "MALE";
}