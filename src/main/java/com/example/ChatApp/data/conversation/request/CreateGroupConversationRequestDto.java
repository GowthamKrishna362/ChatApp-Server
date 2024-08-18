package com.example.ChatApp.data.conversation.request;


import java.util.List;

public record CreateGroupConversationRequestDto(String fromUsername, List<String> targetUsernames, String conversationName) {

}
