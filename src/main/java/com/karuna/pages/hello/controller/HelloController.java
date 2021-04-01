package com.karuna.pages.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "Greetings from Karuna Pages!";
    }

    @RequestMapping(value = "/guest", method = RequestMethod.GET)
    public String helloGuest(){
        return "Greetings Guest!";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String helloSuperAdmin(){
        return "Greetings Super Admin!";
    }
}
