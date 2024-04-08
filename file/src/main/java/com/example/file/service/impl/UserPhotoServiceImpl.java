package com.example.file.service.impl;

import com.example.file.model.entity.UserPhoto;
import com.example.file.repository.UserPhotoRepository;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserPhotoRepository repository;

    @Override
    public void save(UserPhoto entity) {
        repository.save(entity);
    }

    @Override
    public List<String> getPaths(UUID profileId) {
        return repository.findPathsByProfileId(profileId);
    }
}
