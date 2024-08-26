package com.example.ChatApp.controllers;

import com.example.ChatApp.data.user.LoginResponseDto;
import com.example.ChatApp.data.user.SliceOfUsers;
import com.example.ChatApp.data.user.LoginRequestDto;
import com.example.ChatApp.models.User;
import com.example.ChatApp.services.impl.UserServiceImpl;
import com.example.ChatApp.services.jwt.JwtService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        try {
            return userService.saveUser(user);
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto Login(@RequestBody LoginRequestDto loginRequestDto) {
         User authenticatedUser = userService.login(loginRequestDto);
         String jwtToken = jwtService.generateToken(authenticatedUser);
         return new LoginResponseDto(jwtToken, jwtService.getExpirationTime());
    }

    @GetMapping("/all/{prefix}")
    @ResponseStatus(HttpStatus.OK)
    public SliceOfUsers getUsersByPrefix(@PathVariable String prefix) {
        return userService.getSliceOfUsersByPrefix(prefix);
    }


}
