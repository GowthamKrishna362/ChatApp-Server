package com.example.ChatApp.utils.validators;

import com.example.ChatApp.utils.StringUtils;
import jakarta.validation.ValidationException;

import java.util.Optional;

public class Validator {
    public static void validateCredentialNotEmpty(String credential, String credentialName){
        if(StringUtils.isNullOrBlank(credential)) {
            throw new ValidationException(credentialName + " cannot be empty");
        }
    }
    public static void validateValueNotAlreadyPresent(Optional<?> value, String valueName) {
        if(value.isPresent()) {
            throw new ValidationException(valueName + " is already present");
        }
    }
    public static void validateValuePresent(Optional<?> value, String valueName) {
        if(value.isEmpty()) {
            throw  new ValidationException(valueName + " is not present");
        }
    }
}
