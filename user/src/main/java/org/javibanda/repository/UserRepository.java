package org.javibanda.repository;

import org.javibanda.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Long countByEmailOrUserName(String email, String userName);

    User getUserByEmail(String email);
}
