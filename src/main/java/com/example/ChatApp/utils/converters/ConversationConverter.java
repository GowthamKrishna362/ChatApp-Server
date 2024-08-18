package com.example.ChatApp.utils.converters;

import com.example.ChatApp.data.socket.ConversationOpenEventDto;
import com.example.ChatApp.models.ConversationOpenEvent;

import java.util.List;

public class ConversationConverter {
    public static ConversationOpenEventDto toConversationOpenEventDto(ConversationOpenEvent conversationOpenEvent) {
        return new ConversationOpenEventDto(conversationOpenEvent);
    }
    public static List<ConversationOpenEventDto> toConversationOpenEventDtoList(List<ConversationOpenEvent> conversationOpenEventList) {
        return conversationOpenEventList.stream().map(ConversationConverter::toConversationOpenEventDto).toList();
    }
}
