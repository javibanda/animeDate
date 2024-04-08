package com.example.file.service;

import com.amazonaws.services.s3.model.S3Object;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface S3Service {

    void uploadFile(String keyName, MultipartFile file, UUID profileId) throws IOException;

    S3Object getFile(String keyName);
}
