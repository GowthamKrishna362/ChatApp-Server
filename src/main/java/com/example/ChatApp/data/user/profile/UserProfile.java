package com.example.ChatApp.data.user.profile;

import com.example.ChatApp.models.User;
import lombok.Data;

@Data
public class UserProfile {
    String id;
    String username;
    String name;
    public UserProfile(User user) {
        this.id = user.getId().toString();
        this.username = user.getUsername();
        this.name = user.getName();
    }
}
