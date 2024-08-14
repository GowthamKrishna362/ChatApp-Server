package com.example.ChatApp.utils.validators;

import com.example.ChatApp.models.User;
import jakarta.validation.ValidationException;

import java.util.List;
import java.util.Objects;

public class ConversationValidator extends Validator {
    public static void validateNotAddingSelfChat(String fromUsername, String targetUsername) {
        if(fromUsername.equals(targetUsername)) {
            throw new ValidationException("Cant add chat with yourself");
        }
    }
    public static void validateRequestedUsersPresent(List<User> users, String fromUsername, String targetUsername) {
        if(users.size() == 2) {
            return;
        }
        if(users.isEmpty()) {
            throw new ValidationException("Neither source nor target username is valid");
        }
        String invalidUsername = Objects.equals(users.getFirst().getUsername(), fromUsername) ?
                targetUsername : fromUsername;
        
        throw  new ValidationException(invalidUsername + " is invalid");

    }

}
