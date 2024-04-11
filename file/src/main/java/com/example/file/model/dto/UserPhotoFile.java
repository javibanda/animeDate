package com.example.file.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserPhotoFile {
    private UUID id;
    private byte[] file;
}
