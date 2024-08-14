package com.example.ChatApp.services;

import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.dto.ConversationDetailsDto;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface ConversationService {
    @Transactional()
    PrivateConversationProfile addNewPrivateConversation(String fromUsername, String targetUsername);
    List<PrivateConversationProfile> getAllConversations(String username);
    ConversationDetailsDto getConversationDetails(UUID conversationId);
}
