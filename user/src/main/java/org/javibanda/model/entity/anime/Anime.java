package org.javibanda.model.entity.anime;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"anime\"")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Anime {
    @Id
    private String name;
}
