package com.example.ChatApp.services.impl;

import com.example.ChatApp.models.Dto.MessageRequestDto;
import com.example.ChatApp.models.Entity.Message;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl {
    @Autowired
    MessageRepository messageRepository;

    public Message addNewMessage(MessageRequestDto messageRequestDto) {
        Validator.validateCredentialNotEmpty(messageRequestDto.getMessageContent(), "Message");
        return messageRepository.save(new Message(
                messageRequestDto.getMessageContent(),
                messageRequestDto.getSender(),
                messageRequestDto.getConversationId(),
                messageRequestDto.getTimestamp()
                )
        );
    }

}
