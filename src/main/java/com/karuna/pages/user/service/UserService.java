package com.karuna.pages.user.service;

import com.karuna.pages.user.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    Collection<AppUser> getAllUsers();

    AppUser getUser(Long id);

    AppUser saveUser(AppUser user);

    AppUser editUser(AppUser user);

    AppUser disableUser(Long id);

    AppUser getLoggedInUser();


}
