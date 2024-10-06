package com.example.ChatApp.data.conversation.response;

import com.example.ChatApp.data.socket.ConversationOpenEventDto;
import com.example.ChatApp.data.socket.TextMessage.MessageResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ConversationMessageDetailsDto {
    private List<MessageResponseDto> messages;
    private List<ConversationOpenEventDto> conversationLastOpenedList;

}