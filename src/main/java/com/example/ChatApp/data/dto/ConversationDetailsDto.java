package com.example.ChatApp.data.dto;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;

import java.util.List;

public record ConversationDetailsDto(BaseConversationProfile conversationMetadata, List<MessageResponseDto> messages) {
}