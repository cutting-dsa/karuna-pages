package com.karuna.pages.user.controller;

import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @GetMapping(path = "/user/", produces = "application/json")
    public Collection<AppUser> getAll(){
        return userService.getAllUsers();
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @PostMapping(value = "/user/create", consumes = "application/json", produces = "application/json")
    public AppUser createUser(@RequestBody AppUser user){
        return userService.saveUser(user);
    }


    @GetMapping(path = "/login", produces = "application/json")
    public AppUser login(@RequestParam String username, @RequestParam String password){
        return userService.getUserEnabled(username, password);
    }

    @PreAuthorize("hasAuthority('SUPER_ADMIN')")
    @GetMapping(path = "/user/test", produces = "application/json")
    public Collection<AppUser> test(){
        return userService.getAllUsers();
    }
}
