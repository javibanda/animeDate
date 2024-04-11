package com.example.file.service;

import com.example.file.model.dto.UserPhotoFile;
import com.example.file.model.entity.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface S3Service {

    void uploadFile(MultipartFile file, UserPhoto userPhotoEntity) throws IOException;

    List<UserPhotoFile> getFiles(UUID profileId);

    void deleteFile(UUID fileId, UUID profileId);
}
