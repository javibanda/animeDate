package org.javibanda.repository;

import org.javibanda.model.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
}
