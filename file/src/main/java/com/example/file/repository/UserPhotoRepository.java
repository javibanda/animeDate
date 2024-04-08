package com.example.file.repository;

import com.example.file.model.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, UUID> {
    @Query("select userPhoto.path from UserPhoto userPhoto where userPhoto.profileId = ?1")
    List<String> findPathsByProfileId(UUID profileId);

}
