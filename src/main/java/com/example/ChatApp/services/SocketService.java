package com.example.ChatApp.services;

import com.example.ChatApp.data.socket.MessageResponseDto;
import com.example.ChatApp.data.socket.PublishMessageRequestDto;

public interface SocketService {
    MessageResponseDto addNewMessage(PublishMessageRequestDto publishMessageRequestDto);
}
