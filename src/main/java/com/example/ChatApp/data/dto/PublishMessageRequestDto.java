package com.example.ChatApp.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PublishMessageRequestDto {

    private final String sender;
    private final String messageContent;
    private final UUID conversationId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private final LocalDateTime timestamp;

}
