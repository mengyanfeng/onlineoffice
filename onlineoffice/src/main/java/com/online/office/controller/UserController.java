package com.online.office.controller;

import com.online.office.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "Hello, world";
    }

    @RequestMapping("/getUser")
    public String getUser() {
        return userService.getUser();
    }
}
