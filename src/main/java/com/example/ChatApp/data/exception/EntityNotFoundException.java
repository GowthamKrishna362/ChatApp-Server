package com.example.ChatApp.data.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EntityNotFoundException extends Exception {
    private final String message;
    public EntityNotFoundException(String entityType, String entityId) {
        this.message = "Error: " + entityType + " " + "does not exist";
    }
}
