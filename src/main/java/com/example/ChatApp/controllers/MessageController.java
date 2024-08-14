package com.example.ChatApp.controllers;

import com.example.ChatApp.data.dto.MessageResponseDto;
import com.example.ChatApp.data.dto.PublishMessageRequestDto;
import com.example.ChatApp.services.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;
    @Autowired private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload PublishMessageRequestDto publishMessageRequestDto) {
        MessageResponseDto messageResponseDto = messageService.addNewMessage(publishMessageRequestDto);
        messagingTemplate.convertAndSend(
                "/topic/" + publishMessageRequestDto.getConversationId(),
                messageResponseDto
        );
    }

}
