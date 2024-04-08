package com.example.file.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.example.file.model.entity.UserPhoto;
import com.example.file.service.S3Service;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class S3ServiceImpl implements S3Service {
    private final AmazonS3 s3client;
    private final UserPhotoService userPhotoService;

    @Override
    public void uploadFile(MultipartFile file, UserPhoto userPhotoEntity) throws IOException {
        var putObjectResult = s3client.putObject(
                "anime-date",
                userPhotoEntity.getPath(),
                file.getInputStream(),
                null);
        userPhotoService.save(userPhotoEntity);
        log.info(putObjectResult.getMetadata());
    }

    @Override
    public List<S3Object> getFiles(UUID profileId) {
        List<String> paths = userPhotoService.getPaths(profileId);
        List<S3Object> s3Objects = new ArrayList<>();
        paths.forEach(path -> s3client.getObject("anime-date", path));
        return s3Objects;
    }
}
