package com.example.ChatApp.services.impl;

import com.example.ChatApp.data.socket.ConversationOpenEventDto;
import com.example.ChatApp.data.socket.MessageResponseDto;
import com.example.ChatApp.data.socket.PublishMessageRequestDto;
import com.example.ChatApp.models.ConversationOpenEvent;
import com.example.ChatApp.models.conversations.BaseConversation;
import com.example.ChatApp.models.Message;
import com.example.ChatApp.models.User;
import com.example.ChatApp.repository.ConversationOpenEventRepository;
import com.example.ChatApp.repository.ConversationRepository;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.services.SocketService;
import com.example.ChatApp.utils.validators.Validator;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SocketServiceImpl implements SocketService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    private ConversationOpenEventRepository conversationOpenEventRepository;

    @Override
    public MessageResponseDto addNewMessage(PublishMessageRequestDto publishMessageRequestDto) {
        User sender = userRepository
                .findByUsername(publishMessageRequestDto.getSender())
                .orElseThrow(() -> new ValidationException("Username not present"));
        BaseConversation conversation = conversationRepository
                .findById(publishMessageRequestDto.getConversationId())
                .orElseThrow(() -> new ValidationException("Username not present"));
        Validator.validateCredentialNotEmpty(publishMessageRequestDto.getMessageContent(), "Message");
        Message message = new Message(
                sender,
                conversation,
                publishMessageRequestDto
        );
         messageRepository.save(message);
         return new MessageResponseDto(message);
    }

    public void registerConversationOpen(ConversationOpenEventDto conversationOpenEventDto) {
        User user = userRepository
                .findByUsername(conversationOpenEventDto.getUsername())
                .orElseThrow(() -> new ValidationException("username not present"));

        BaseConversation conversation = conversationRepository
                .findById(conversationOpenEventDto.getConversationId())
                .orElseThrow(() -> new ValidationException("conversation not present"));

        Optional<ConversationOpenEvent> optionalConversationOpenEvent =
                conversationOpenEventRepository.findByConversationAndUser(conversation, user);
        ConversationOpenEvent conversationOpenEvent;

        if(optionalConversationOpenEvent.isPresent()){
            conversationOpenEvent = optionalConversationOpenEvent.get();
            conversationOpenEvent.setLastOpenedAt(conversationOpenEventDto.getTimeStamp());
        } else {
            conversationOpenEvent = new ConversationOpenEvent(
                    user,
                    conversation,
                    conversationOpenEventDto.getTimeStamp()
            );
        }
        conversationOpenEventRepository.save(conversationOpenEvent);
    }
}
