package com.example.ChatApp.data.exception;

public class ConversationNotFoundException extends EntityNotFoundException {
    public ConversationNotFoundException(Long conversationId) {
        super("Conversation", conversationId.toString());
    }
}
