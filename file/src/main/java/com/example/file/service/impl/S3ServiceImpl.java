package com.example.file.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.example.file.mapper.UserPhotoMapper;
import com.example.file.service.S3Service;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class S3ServiceImpl implements S3Service {
    private final AmazonS3 s3client;
    private final UserPhotoService userPhotoService;

    @Override
    public void uploadFile(String keyName, MultipartFile file, UUID profileId) throws IOException {
        var putObjectResult = s3client.putObject("anime-date", keyName, file.getInputStream(), null);
        userPhotoService.save(UserPhotoMapper.toEntity(keyName, profileId));
        log.info(putObjectResult.getMetadata());
    }

    @Override
    public S3Object getFile(String keyName) {
        keyName = "carpeta/"+keyName;
        return s3client.getObject("anime-date", keyName);
    }
}
