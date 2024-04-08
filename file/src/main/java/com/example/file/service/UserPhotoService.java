package com.example.file.service;

import com.example.file.model.entity.UserPhoto;

import java.util.List;
import java.util.UUID;

public interface UserPhotoService {

    void save(UserPhoto entity);

    List<String> getPaths(UUID profileId);
}
