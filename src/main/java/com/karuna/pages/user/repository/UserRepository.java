package com.karuna.pages.user.repository;

import com.karuna.pages.user.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser getUserByUsername(String username);

    AppUser getUserById(Long id);

    Collection<AppUser> getAppUsersByUsernameAndEnabled(String username, Integer enabled);
}
