package com.example.file.service.impl;

import com.example.file.model.entity.UserPhoto;
import com.example.file.repository.UserPhotoRepository;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserPhotoRepository repository;


    @Override
    public void save(UserPhoto entity) {
        repository.save(entity);
    }
}
