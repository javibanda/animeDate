package org.javibanda.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"anime\"")
@Getter
@Setter
public class Anime {
    @Id
    private String name;
}
