package com.example.ChatApp.data.exception;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String username) {
        super("User", username);
    }
}
