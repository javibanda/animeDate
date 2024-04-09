package com.example.file.service.impl;

import com.example.file.model.dto.UserPhotoOrder;
import com.example.file.model.entity.UserPhoto;
import com.example.file.repository.UserPhotoRepository;
import com.example.file.service.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<String> getPaths(UUID profileId) {
        return repository.findPathsByProfileId(profileId);
    }

    @Override
    public Integer getCountUserPhoto(UUID profileId) {
        return repository.countByProfileId(profileId);
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public void orderUserPhoto(List<UserPhotoOrder> userPhotoOrders, UUID profileId) throws Exception {
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
         return repository.findUserPhotoById(id);
    }



    private void checkOrder(List<UserPhotoOrder> userPhotoOrders, UUID profileId) throws Exception {
        List<Integer> orders = userPhotoOrders.stream()
                .map(UserPhotoOrder::getOrder)
                .collect(Collectors.toList());
        HashSet<Integer> numbers = new HashSet<>(orders);
        Integer countUserPhoto = getCountUserPhoto(profileId);
        if (orders.size() != countUserPhoto){
            throw new Exception("sdf");
        }
        for (Integer number : numbers) {
            if (number < 0 || number >= countUserPhoto){
                throw new Exception("asdf");
            }
            if (Collections.frequency(orders, number) != 1){
                throw new Exception("asdf");
            }
        }
    }
}
