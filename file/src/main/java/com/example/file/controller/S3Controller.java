package com.example.file.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.example.file.mapper.UserPhotoMapper;
import com.example.file.service.S3Service;
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


    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadUserPhoto(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("profile") UUID profileId) throws IOException {

        String BASE_PATH = "profile/";
        String path = BASE_PATH + profileId.toString() + "/";
        String fileName = UUID.randomUUID() + ".jpg";

        s3Service.uploadFile(file, UserPhotoMapper.toEntity(path + fileName, profileId));
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/download/{profileId}")
    public ResponseEntity<List<byte[]>> getUserPhoto(@PathVariable UUID profileId) throws IOException {
        var s3Objects = s3Service.getFiles(profileId);
        val images = new ArrayList<byte[]>();
        for (S3Object s3Object : s3Objects){
            var content = s3Object.getObjectContent();
            images.add(IOUtils.toByteArray(content));
        }
        return ResponseEntity.ok()
                .body(images);
    }

}
