package com.example.file.controller;

import com.amazonaws.util.IOUtils;
import com.example.file.mapper.UserPhotoMapper;
import com.example.file.model.dto.UserPhotoOrder;
import com.example.file.service.S3Service;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class S3Controller {

    private final S3Service s3Service;
    private final UserPhotoService userPhotoService;


    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadUserPhoto(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("profile") UUID profileId) throws IOException {

        s3Service.uploadFile(file, UserPhotoMapper.toEntity(profileId, UUID.randomUUID()));
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/download/{profileId}")
    public ResponseEntity<List<byte[]>> getUserPhotos(@PathVariable UUID profileId) {
        val s3Objects = s3Service.getFiles(profileId);
        val images = new ArrayList<byte[]>();

        s3Objects.forEach( s3Object -> {
            try {
                images.add(IOUtils.toByteArray(s3Object.getObjectContent()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseEntity.ok()
                .body(images);
    }

    @PostMapping("/order_by/{profileId}")
    public ResponseEntity<String> orderUserPhotos(@PathVariable UUID profileId,
                                                  @RequestBody List<UserPhotoOrder> userPhotoOrder) throws Exception {
        userPhotoService.orderUserPhoto(userPhotoOrder, profileId);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/delete/{fileId}")
    public ResponseEntity<String> deleteUserPhoto(@PathVariable UUID fileId) {
        s3Service.deleteFile(fileId);
        return ResponseEntity.ok("OK");
    }




}
