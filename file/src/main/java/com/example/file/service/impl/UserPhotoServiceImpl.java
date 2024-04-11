package com.example.file.service.impl;

import com.example.file.model.dto.UserPhotoOrder;
import com.example.file.model.entity.UserPhoto;
import com.example.file.repository.UserPhotoRepository;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserPhotoRepository repository;

    @Override
    public void save(UserPhoto entity) {
        repository.save(entity);
    }

    @Override
    public List<UserPhoto> getUserPhotoByProfileId(UUID profileId) {
        return repository.findUserPhotoByProfileIdOrderByOrder(profileId);
    }

    @Override
    public Integer getCountUserPhoto(UUID profileId) {
        return repository.countByProfileId(profileId);
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void orderUserPhoto(List<UserPhotoOrder> userPhotoOrders, UUID profileId) {
        checkOrder(userPhotoOrders, profileId);
        userPhotoOrders.forEach( userPhotoOrder ->
                repository.updateOrder(userPhotoOrder.getId(), userPhotoOrder.getOrder()));
    }

    @Override
    public void deleteUserPhoto(UserPhoto userPhoto) {
        repository.delete(userPhoto);
        Integer count = userPhoto.getOrder() + 1;
        while (repository.findUserPhotoByProfileIdAndOrder(userPhoto.getProfileId(), count) != null){
            repository.subtractOrder(userPhoto.getProfileId(), count++);
        }
    }

    @Override
    public UserPhoto getUserPhoto(UUID id) {
         val userPhoto = repository.findUserPhotoById(id);
         if (userPhoto == null){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not exist");
         }
         return userPhoto;
    }

    @Override
    public boolean isNotFileFromProfile(UUID fileId, UUID profileId) {
        return !repository.existsByIdAndProfileId(fileId, profileId);
    }

    private void checkOrder(List<UserPhotoOrder> userPhotoOrders, UUID profileId){
        List<Integer> orders = userPhotoOrders.stream()
                .map(UserPhotoOrder::getOrder)
                .collect(Collectors.toList());
        HashSet<Integer> numbers = new HashSet<>(orders);
        Integer countUserPhoto = getCountUserPhoto(profileId);
        if (isRequestCountDistinctFileSaved(orders.size(), countUserPhoto)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number of files saved is " + countUserPhoto);
        }
        for (Integer number : numbers) {
            if (isNotRequestCountBetweenZeroAndCountUserPhoto(number, countUserPhoto)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number " + number + " is not posible");
            }
            if (isRequestCountDuplicated(orders, number)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The number " + number + " is duplicated");
            }
        }
    }

    private boolean isRequestCountDistinctFileSaved(Integer requestSize, Integer countUserPhoto){
        return !Objects.equals(requestSize, countUserPhoto);
    }

    private boolean isNotRequestCountBetweenZeroAndCountUserPhoto(Integer requestCount, Integer countUserPhoto){
        return requestCount < 0 || requestCount >= countUserPhoto;
    }

    private boolean isRequestCountDuplicated(List<Integer> orders, Integer requestCountWithoutRepeat){
        return Collections.frequency(orders, requestCountWithoutRepeat) != 1;
    }
}
