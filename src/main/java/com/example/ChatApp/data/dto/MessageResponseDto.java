package com.example.ChatApp.data.dto;

import com.example.ChatApp.models.Entity.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponseDto {
    private String id;
    private String conversationId;
    private String sender;
    private String messageContent;
    private LocalDateTime timestamp;
    public MessageResponseDto(Message message) {
        this.id = message.getId().toString();
        this.conversationId = message.getConversation().getId().toString();
        this.sender = message.getSender().getUsername();
        this.messageContent = message.getMessageContent();
        this.timestamp = message.getTimestamp();
    }
}
