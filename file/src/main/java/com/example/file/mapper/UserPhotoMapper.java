package com.example.file.mapper;

import com.example.file.model.entity.UserPhoto;

import java.sql.Timestamp;
import java.util.UUID;

public class UserPhotoMapper {

    public static UserPhoto toEntity(String path, UUID profileId){
        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(UUID.randomUUID());
        userPhoto.setPath(path);
        userPhoto.setDate(new Timestamp(System.currentTimeMillis()));
        userPhoto.setProfileId(profileId);
        return userPhoto;
    }
}
