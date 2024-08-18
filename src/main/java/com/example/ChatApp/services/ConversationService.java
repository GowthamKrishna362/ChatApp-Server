package com.example.ChatApp.services;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.conversation.response.ConversationMessageDetailsDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ConversationService {
    @Transactional()
    PrivateConversationProfile addNewPrivateConversation(String fromUsername, String targetUsername);
    GroupConversationProfile addNewGroupConversation(String fromUsername, List<String> targetUsernames, String name);
    List<BaseConversationProfile> getAllConversations(String username);
    ConversationMessageDetailsDto getConversationMessageDetails(Long conversationId);
}
