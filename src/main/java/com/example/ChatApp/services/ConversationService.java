package com.example.ChatApp.services;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.conversation.request.CreateGroupConversationRequestDto;
import com.example.ChatApp.data.conversation.response.ConversationMessageDetailsDto;
import com.example.ChatApp.data.exception.ConversationNotFoundException;
import com.example.ChatApp.data.exception.UserNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ConversationService {
    @Transactional()
    PrivateConversationProfile addNewPrivateConversation(String fromUsername, String targetUsername) throws UserNotFoundException;
    GroupConversationProfile addNewGroupConversation(CreateGroupConversationRequestDto createGroupConversationRequestDto) throws UserNotFoundException;
    List<BaseConversationProfile> getAllConversations(String username) throws UserNotFoundException;
    ConversationMessageDetailsDto getConversationMessageDetails(Long conversationId) throws ConversationNotFoundException;
}
