package com.karuna.pages.user.controller;

import com.karuna.pages.role.model.Role;
import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/", produces = "application/json")
    public Collection<AppUser> getAll(){
        return userService.getAllUsers();
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public AppUser createUser(@RequestBody AppUser user){
        return userService.saveUser(user);
    }
}
