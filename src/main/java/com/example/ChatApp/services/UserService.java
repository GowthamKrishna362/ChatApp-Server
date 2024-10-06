package com.example.ChatApp.services;

import com.example.ChatApp.data.exception.UserNotFoundException;
import com.example.ChatApp.data.user.SliceOfUsers;
import com.example.ChatApp.data.user.LoginRequestDto;
import com.example.ChatApp.models.User;

public interface UserService {

    User login(LoginRequestDto loginRequestDto) throws UserNotFoundException;
    User saveUser(User user);
    SliceOfUsers getSliceOfUsersByPrefix(String prefix);
}
