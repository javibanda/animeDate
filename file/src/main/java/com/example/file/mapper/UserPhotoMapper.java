package com.example.file.mapper;

import com.example.file.model.entity.UserPhoto;

import java.sql.Timestamp;
import java.util.UUID;

public class UserPhotoMapper {

    public static UserPhoto toEntity(UUID profileId, UUID fileId){
        String path = "profile/" + profileId.toString() + "/" + fileId + ".jpg";

        UserPhoto userPhoto = new UserPhoto();
        userPhoto.setId(fileId);
        userPhoto.setPath(path);
        userPhoto.setDate(new Timestamp(System.currentTimeMillis()));
        userPhoto.setProfileId(profileId);
        return userPhoto;
    }

    public static UserPhoto setOrderPhoto(UserPhoto entity, Integer order){
        entity.setOrder(order);
        return entity;
    }
}
