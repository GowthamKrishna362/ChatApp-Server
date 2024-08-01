package com.example.ChatApp.Controllers;

import com.example.ChatApp.Models.Entity.User;
import com.example.ChatApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User login(@RequestBody User user) {
        return user;
    }


}
