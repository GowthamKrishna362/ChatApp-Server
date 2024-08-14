package com.example.ChatApp.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MessageResponseDto {
    private Long id;
    private Long conversationId;
    private String sender;
    private String messageContent;
    private LocalDateTime timestamp;
}
