package com.example.ChatApp.services;

import com.example.ChatApp.models.Dto.MessageResponseDto;
import com.example.ChatApp.models.Entity.Conversation;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ConversationServiceI {
    @Transactional()
    Conversation addNewConversation(String fromUsername, String targetUsername);

    List<Conversation> getAllConversations(String username);

    List<MessageResponseDto> getConversationMessages(Long conversationId);
}
