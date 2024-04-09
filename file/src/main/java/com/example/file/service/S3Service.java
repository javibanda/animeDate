package com.example.file.service;

import com.amazonaws.services.s3.model.S3Object;
import com.example.file.model.entity.UserPhoto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface S3Service {

    void uploadFile(MultipartFile file, UserPhoto userPhotoEntity) throws IOException;

    List<S3Object> getFiles(UUID profileId);

    void deleteFile(UUID fileId);
}
