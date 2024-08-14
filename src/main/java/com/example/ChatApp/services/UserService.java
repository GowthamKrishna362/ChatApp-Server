package com.example.ChatApp.services;

import com.example.ChatApp.data.user.request.LoginRequestDto;
import com.example.ChatApp.models.User;

public interface UserService {

    void login(LoginRequestDto loginRequestDto);

    User saveUser(User user);
}
