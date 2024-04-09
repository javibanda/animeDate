package com.example.file.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "user_photo")
@Getter
@Setter
public class UserPhoto {

    @Id
    @Column(name = "id", columnDefinition = "uuid", nullable = false)
    private UUID id;

    private String path;

    private Timestamp date;
    @Column(name = "\"order\"")
    private Integer order;

    private UUID profileId;

}
