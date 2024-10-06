package com.example.ChatApp.controllers;

import com.example.ChatApp.data.exception.ConversationNotFoundException;
import com.example.ChatApp.data.socket.ConversationOpenEventDto;
import com.example.ChatApp.data.socket.TextMessage.MessageResponseDto;
import com.example.ChatApp.data.socket.TextMessage.PublishMessageRequestDto;
import com.example.ChatApp.data.socket.VideoConversation.PeerIdDto;
import com.example.ChatApp.data.socket.VideoConversation.VideoCallDto;
import com.example.ChatApp.models.User;
import com.example.ChatApp.services.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;


@Controller
public class MessageController {

    @Autowired
    MessageServiceImpl socketService;
    @Autowired private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload PublishMessageRequestDto publishMessageRequestDto, Authentication authentication) throws ConversationNotFoundException {

        MessageResponseDto messageResponseDto = socketService.addNewMessage(publishMessageRequestDto, (User) authentication.getPrincipal());
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

    @MessageMapping("video/request")
    public void videoChatRequest(@Payload VideoCallDto initiateVideoCallDto, Authentication authentication) {
        User sender = (User) authentication.getPrincipal();
        initiateVideoCallDto.setSender(sender.getUsername());
        messagingTemplate.convertAndSend("/topic/user/" + initiateVideoCallDto.getRecipient(), initiateVideoCallDto );
    }

    @MessageMapping("video/peerId")
    public void sharePeerId(@Payload PeerIdDto peerIdDto, Authentication authentication) {
        User sender = (User) authentication.getPrincipal();
        peerIdDto.setSender(sender.getUsername());
        messagingTemplate.convertAndSend("/topic/user/" + peerIdDto.getRecipient(), peerIdDto);
    }

}
