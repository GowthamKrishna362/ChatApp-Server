package com.example.ChatApp.services.impl;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.dto.MessageResponseDto;
import com.example.ChatApp.data.dto.PublishMessageRequestDto;
import com.example.ChatApp.models.BaseConversation;
import com.example.ChatApp.models.PrivateConversation;
import com.example.ChatApp.models.Message;
import com.example.ChatApp.models.User;
import com.example.ChatApp.repository.ConversationRepository;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.services.MessageService;
import com.example.ChatApp.utils.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConversationRepository conversationRepository;

    @Override
    public MessageResponseDto addNewMessage(PublishMessageRequestDto publishMessageRequestDto) {
        Optional<User> sender = userRepository.findByUsername(publishMessageRequestDto.getSender());
        Optional <BaseConversation> conversation = conversationRepository.findById(publishMessageRequestDto.getConversationId());
        Validator.validateValuePresent(sender, "Username");
        Validator.validateValuePresent(conversation, "Conversation Id");
        Validator.validateCredentialNotEmpty(publishMessageRequestDto.getMessageContent(), "Message");
        Message message = new Message(
                publishMessageRequestDto.getMessageContent(),
                sender.orElse(null),
                conversation.orElse(null),
                publishMessageRequestDto.getTimestamp()
        );
         messageRepository.save(message);
         return new MessageResponseDto(message);
    }
}
