package com.karuna.pages.user.controller;

import com.karuna.pages.user.model.AppUser;
import com.karuna.pages.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public Collection<AppUser> getAll(){
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, consumes = "application/json")
    public AppUser createUser(@RequestBody AppUser user){
        return userService.saveUser(user);
    }
}
