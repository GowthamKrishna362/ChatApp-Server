package com.example.ChatApp.controllers;

import com.example.ChatApp.data.socket.ConversationOpenEventDto;
import com.example.ChatApp.data.socket.MessageResponseDto;
import com.example.ChatApp.data.socket.PublishMessageRequestDto;
import com.example.ChatApp.services.impl.SocketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class SocketController {

    @Autowired
    SocketServiceImpl socketService;
    @Autowired private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload PublishMessageRequestDto publishMessageRequestDto) {
        MessageResponseDto messageResponseDto = socketService.addNewMessage(publishMessageRequestDto);
        messageResponseDto.setTempId(publishMessageRequestDto.getTempId());

        messagingTemplate.convertAndSend(
                "/topic/" + publishMessageRequestDto.getConversationId(),
                messageResponseDto
        );
    }

    @MessageMapping("/event")
    public void groupOpen(@Payload ConversationOpenEventDto conversationOpenEventDto) {
        socketService.registerConversationOpen(conversationOpenEventDto);
        messagingTemplate.convertAndSend(
                "/topic/" + conversationOpenEventDto.getConversationId(),
                conversationOpenEventDto
        );
    }

}
