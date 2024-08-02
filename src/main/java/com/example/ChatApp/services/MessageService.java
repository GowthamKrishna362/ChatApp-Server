package com.example.ChatApp.services;

import com.example.ChatApp.Models.Dto.MessageRequestDto;
import com.example.ChatApp.Models.Entity.Message;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
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
