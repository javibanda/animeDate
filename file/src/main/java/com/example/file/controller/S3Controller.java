package com.example.file.controller;

import com.example.file.mapper.UserPhotoMapper;
import com.example.file.model.dto.UserPhotoFile;
import com.example.file.model.dto.UserPhotoOrder;
import com.example.file.service.S3Service;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user_file")
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

    @GetMapping("/{profileId}")
    public ResponseEntity<List<UserPhotoFile>> getUserPhotos(@PathVariable UUID profileId) {
        return ResponseEntity.ok().body(s3Service.getFiles(profileId));
    }

    @PutMapping("/order_by/{profileId}")
    public ResponseEntity<String> orderUserPhotos(@PathVariable UUID profileId,
                                                  @RequestBody List<UserPhotoOrder> userPhotoOrder){
        try {
            userPhotoService.orderUserPhoto(userPhotoOrder, profileId);
            return ResponseEntity.ok("OK");
        }catch (ResponseStatusException e){
            return ResponseEntity.status(e.getStatus()).body(e.getReason());
        }
    }

    @DeleteMapping("/delete/{fileId}/{profileId}")
    public ResponseEntity<String> deleteUserPhoto(@PathVariable UUID fileId,
                                                  @PathVariable UUID profileId) {
        s3Service.deleteFile(fileId, profileId);
        return ResponseEntity.ok("OK");
    }




}
