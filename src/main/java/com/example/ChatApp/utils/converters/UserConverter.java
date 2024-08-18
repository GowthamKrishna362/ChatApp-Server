package com.example.ChatApp.utils.converters;

import com.example.ChatApp.data.user.profile.UserProfile;
import com.example.ChatApp.models.User;

import java.util.List;

public class UserConverter {
    public static UserProfile toUserProfile(User user){
        return new UserProfile(user);
    }
    public static List<UserProfile> toUserProfileList(List<User> users) {
        return users.stream().map(UserConverter::toUserProfile).toList();
    }
}
