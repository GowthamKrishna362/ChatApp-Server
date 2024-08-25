package com.example.ChatApp.services.mappers;

import com.example.ChatApp.data.user.UserProfile;
import com.example.ChatApp.models.User;

import java.util.List;

public class UserMapper {
    public static UserProfile toUserProfile(User user){
        return new UserProfile(user);
    }
    public static List<UserProfile> toUserProfileList(List<User> users) {
        return users.stream().map(UserMapper::toUserProfile).toList();
    }
}
