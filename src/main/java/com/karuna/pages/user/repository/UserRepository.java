package com.karuna.pages.user.repository;

import com.karuna.pages.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser getUserByUsername(String username);

    AppUser getUserById(Long id);
}
