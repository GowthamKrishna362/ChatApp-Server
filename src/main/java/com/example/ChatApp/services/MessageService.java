package com.example.ChatApp.services;

import com.example.ChatApp.data.dto.MessageResponseDto;
import com.example.ChatApp.data.dto.PublishMessageRequestDto;

public interface MessageService {
    MessageResponseDto addNewMessage(PublishMessageRequestDto publishMessageRequestDto);
}
