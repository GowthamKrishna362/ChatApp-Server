package com.example.ChatApp.services;

import com.example.ChatApp.data.user.SliceOfUsers;
import com.example.ChatApp.data.user.profile.UserProfile;
import com.example.ChatApp.data.user.request.LoginRequestDto;
import com.example.ChatApp.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserService {

    void login(LoginRequestDto loginRequestDto);
    User saveUser(User user);
    SliceOfUsers getSliceOfUsersByPrefix(String prefix);
}
