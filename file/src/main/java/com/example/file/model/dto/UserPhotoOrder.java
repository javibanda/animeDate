package com.example.file.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserPhotoOrder {
    private UUID id;
    private Integer order;
}
