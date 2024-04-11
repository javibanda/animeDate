package com.example.file.repository;

import com.example.file.model.entity.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserPhotoRepository extends JpaRepository<UserPhoto, UUID> {

    List<UserPhoto> findUserPhotoByProfileIdOrderByOrder(UUID profileId);

    Integer countByProfileId(UUID profileId);

    @Modifying
    @Query("update UserPhoto userPhoto set userPhoto.order = ?2 where userPhoto.id = ?1")
    void updateOrder(UUID id, Integer order);

    @Modifying
    @Query("update UserPhoto userPhoto set userPhoto.order = (?2 - 1) where userPhoto.profileId = ?1 and userPhoto.order = ?2")
    void subtractOrder(UUID profileId, Integer order);

    UserPhoto findUserPhotoById(UUID id);

    UserPhoto findUserPhotoByProfileIdAndOrder(UUID profileId, Integer order);

    boolean existsByIdAndProfileId(UUID id, UUID profileId);

}
