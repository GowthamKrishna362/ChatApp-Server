package com.example.ChatApp.utils.validators;

import com.example.ChatApp.models.User;

public class UserValidator extends Validator {
    public static void checkValidCredentials(User user, boolean isNewUser) {
        validateCredentialNotEmpty(user.getUsername(), "Username");
        validateCredentialNotEmpty(user.getPassword(), "Password");
        if(isNewUser) {
            validateCredentialNotEmpty(user.getName(), "name");
        }
    }
}
