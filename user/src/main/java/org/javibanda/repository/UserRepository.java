package org.javibanda.repository;

import org.javibanda.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Long countByEmailOrUserName(String email, String userName);

    User getUserByEmail(String email);
    @Query("select u.userName from User u where u.id = ?1")
    String getNameById(UUID id);
}
