package com.example.ChatApp.utils.converters;

import com.example.ChatApp.data.socket.MessageResponseDto;
import com.example.ChatApp.models.Message;

import java.util.List;

public class MessageConverter {
    public static MessageResponseDto toMessageResponseDto(Message message) {
        return new MessageResponseDto(message);
    }
    public static List<MessageResponseDto> toMessageResponseDtoList(List<Message> messages) {
        return messages.stream().map(MessageConverter::toMessageResponseDto).toList();
    }
}
