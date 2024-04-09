package com.example.file.service;

import com.example.file.model.dto.UserPhotoOrder;
import com.example.file.model.entity.UserPhoto;

import java.util.List;
import java.util.UUID;

public interface UserPhotoService {

    void save(UserPhoto entity);

    List<String> getPaths(UUID profileId);

    Integer getCountUserPhoto(UUID profileId);

    void orderUserPhoto(List<UserPhotoOrder> userPhotoOrders, UUID profileId) throws Exception;

    void deleteUserPhoto(UserPhoto userPhoto);

    UserPhoto getUserPhoto(UUID id);
}
