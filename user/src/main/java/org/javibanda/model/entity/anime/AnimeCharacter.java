package org.javibanda.model.entity.anime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.javibanda.model.enums.Sex;

import javax.persistence.*;

@Entity
@Table(name = "anime_character")
@Getter
@Setter
@JsonInclude(JsonInclude.Include. NON_NULL)
public class AnimeCharacter {
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "url_photo")
    private String urlPhoto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "anime", nullable = false)
    private Anime anime;
    @Column(name = "sex", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Sex sex = Sex.MALE;
}