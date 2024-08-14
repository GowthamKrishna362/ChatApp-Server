package com.example.ChatApp.services.impl;

import com.example.ChatApp.models.Dto.MessageResponseDto;
import com.example.ChatApp.models.Entity.Conversation;
import com.example.ChatApp.models.Entity.ConversationMember;
import com.example.ChatApp.models.Entity.Message;
import com.example.ChatApp.models.Entity.User;
import com.example.ChatApp.repository.ConversationMemberRepository;
import com.example.ChatApp.repository.ConversationRepository;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.utils.converters.ConversationConverter;
import com.example.ChatApp.utils.validators.ConversationValidator;
import com.example.ChatApp.utils.validators.Validator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ConversationServiceImpl {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    ConversationMemberRepository conversationMemberRepository;
    @Autowired
    MessageRepository messageRepository;

    private void validateUsers( String fromUsername, String targetUsername) {
        List<String> usernameList = Arrays.asList(fromUsername, targetUsername);
        List<User> fetchedUsers = userRepository.findByUsernames(usernameList);
        Validator.validateCredentialNotEmpty(fromUsername, "from username");
        Validator.validateCredentialNotEmpty(targetUsername, "target username");
        ConversationValidator.validateNotAddingSelfChat(fromUsername, targetUsername);
        ConversationValidator.validateRequestedUsersPresent(fetchedUsers, fromUsername, targetUsername);
    }

    private Conversation saveConversation( String fromUsername, String targetUsername) {
        Conversation conversation = new Conversation(fromUsername + "-" + targetUsername);
        Conversation savedConversation = conversationRepository.save(conversation);
        List<ConversationMember> conversationMembers = Arrays.asList(
                new ConversationMember(fromUsername, savedConversation.getId()),
                new ConversationMember(targetUsername, savedConversation.getId())
        );
        conversationMemberRepository.saveAll(conversationMembers);
        return savedConversation;
    }

    @Transactional()
    public Conversation addNewConversation(String fromUsername, String targetUsername) {
        validateUsers( fromUsername, targetUsername);
        return saveConversation(fromUsername, targetUsername);
    }


    public List<Conversation> getAllConversations(String username) {
        return conversationMemberRepository.findConversationsByUsername(username);
    }

    public List<MessageResponseDto> getConversationMessages(Long conversationId) {
        List<Message> messages = messageRepository.getMessagesByConversationId(conversationId);
        return ConversationConverter.convertMessagesToDto(messages);
    }
}
