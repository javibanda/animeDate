package com.example.file.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.util.IOUtils;
import com.example.file.mapper.UserPhotoMapper;
import com.example.file.model.dto.UserPhotoFile;
import com.example.file.model.entity.UserPhoto;
import com.example.file.service.S3Service;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
    @Value("${aws.bucket}")
    String awsBucket;

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void uploadFile(MultipartFile file, UserPhoto userPhotoEntity) throws IOException {
        userPhotoService.save(
                UserPhotoMapper.setOrderPhoto(
                        userPhotoEntity,
                        userPhotoService.getCountUserPhoto(userPhotoEntity.getProfileId())
                )
        );
        var putObjectResult = s3client.putObject(
                awsBucket,
                userPhotoEntity.getPath(),
                file.getInputStream(),
                null);
        log.info(putObjectResult.getMetadata());
    }

    @Override
    public List<UserPhotoFile> getFiles(UUID profileId) {
        List<UserPhoto> userPhotos = userPhotoService.getUserPhotoByProfileId(profileId);
        List<UserPhotoFile> files = new ArrayList<>();
        userPhotos.forEach(userPhoto -> {
            val s3Object =  s3client.getObject(awsBucket, userPhoto.getPath());
            try {
                files.add(new UserPhotoFile(userPhoto.getId(), IOUtils.toByteArray(s3Object.getObjectContent())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return files;
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void deleteFile(UUID fileId, UUID profileId) {
        UserPhoto userPhoto = userPhotoService.getUserPhoto(fileId);
        if (userPhotoService.isNotFileFromProfile(fileId, profileId)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED");
        }
        userPhotoService.deleteUserPhoto(userPhoto);
        s3client.deleteObject(awsBucket, userPhoto.getPath());
    }
}
