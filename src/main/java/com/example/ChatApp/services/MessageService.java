package com.example.ChatApp.services;

import com.example.ChatApp.data.exception.ConversationNotFoundException;
import com.example.ChatApp.data.socket.TextMessage.MessageResponseDto;
import com.example.ChatApp.data.socket.TextMessage.PublishMessageRequestDto;
import com.example.ChatApp.models.User;

public interface MessageService {
    MessageResponseDto addNewMessage(PublishMessageRequestDto publishMessageRequestDto, User sender) throws ConversationNotFoundException;
}
