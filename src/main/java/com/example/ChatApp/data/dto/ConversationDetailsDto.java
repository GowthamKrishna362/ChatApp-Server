package com.example.ChatApp.data.dto;

import com.example.ChatApp.models.Entity.Conversation;

import java.util.List;

public class ConversationResponseDto {
    private Conversation conversation;
    private List<UserResponseDto> members;
}
