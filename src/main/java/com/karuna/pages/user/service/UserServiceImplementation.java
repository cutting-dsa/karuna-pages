package com.karuna.pages.user.service;

import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppUser getUser(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public AppUser editUser(AppUser user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public AppUser disbaleUser(Long id) {
        AppUser user =  userRepository.getUserById(id);

        return userRepository.saveAndFlush(user);
    }
}
