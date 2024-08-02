package com.example.ChatApp.utils.validators;

import com.example.ChatApp.Models.Entity.User;
import jakarta.validation.ValidationException;

import java.util.List;

public class ConversationValidator extends Validator {
    public static void validateNotAddingSelfChat(String fromUsername, String targetUsername) {
        if(fromUsername.equals(targetUsername)) {
            throw new ValidationException("Cant add chat with yourself");
        }
    }
    public static void validateRequestedUsersPresent(List<User> users, String fromUsername, String toUsername) {

    }

}
