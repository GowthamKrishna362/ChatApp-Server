package com.example.ChatApp.utils.converters;

import com.example.ChatApp.data.dto.MessageResponseDto;
import com.example.ChatApp.models.Entity.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageConverter {

    public static List<MessageResponseDto> convertMessageListToDto(List<Message> messages) {
        List<MessageResponseDto> result = new ArrayList<>();
        for(Message message : messages ) {
            MessageResponseDto messageResponseDto = new MessageResponseDto(message);
            result.add(messageResponseDto);
        }
        return result;
    }

}
