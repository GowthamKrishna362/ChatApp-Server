package com.example.ChatApp.services.mappers;

import com.example.ChatApp.data.socket.TextMessage.MessageResponseDto;
import com.example.ChatApp.models.Message;

import java.util.List;

public class MessageMapper {
    public static MessageResponseDto toMessageResponseDto(Message message) {
        return new MessageResponseDto(message);
    }
    public static List<MessageResponseDto> toMessageResponseDtoList(List<Message> messages) {
        return messages.stream().map(MessageMapper::toMessageResponseDto).toList();
    }
}
