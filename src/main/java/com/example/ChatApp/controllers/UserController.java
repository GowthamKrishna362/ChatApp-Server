package com.example.ChatApp.controllers;

import com.example.ChatApp.data.dto.LoginRequestDto;
import com.example.ChatApp.models.Entity.User;
import com.example.ChatApp.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody LoginRequestDto loginRequestDto) {
         userService.login(loginRequestDto);
    }


}
