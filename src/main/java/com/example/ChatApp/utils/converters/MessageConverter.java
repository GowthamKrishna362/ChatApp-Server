package com.example.ChatApp.utils.converters;

import com.example.ChatApp.data.dto.MessageResponseDto;
import com.example.ChatApp.models.Entity.Message;

import java.util.ArrayList;
import java.util.List;

public class ConversationConverter {
    public static List<MessageResponseDto> convertMessagesToDto(List<Message> messages) {
        List<MessageResponseDto> result = new ArrayList<>();
        for(Message message : messages ) {
            MessageResponseDto messageResponseDto = new MessageResponseDto(
                    message.getId(),
                    message.getConversation().getId(),
                    message.getUser().getUsername(),
                    message.getMessageContent(),
                    message.getTimestamp()
                    );

            result.add(messageResponseDto);
        }
        return result;
    }
}
