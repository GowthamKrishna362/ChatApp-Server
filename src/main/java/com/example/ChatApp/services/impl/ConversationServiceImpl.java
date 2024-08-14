package com.example.ChatApp.services.impl;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.conversation.response.ConversationDetailsDto;
import com.example.ChatApp.data.dto.MessageResponseDto;
import com.example.ChatApp.data.enums.ConversationType;
import com.example.ChatApp.models.*;
import com.example.ChatApp.repository.ConversationRepository;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.services.ConversationService;
import com.example.ChatApp.utils.validators.ConversationValidator;
import com.example.ChatApp.utils.validators.Validator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConversationServiceImpl implements ConversationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    MessageRepository messageRepository;

    @Override
    @Transactional()
    public PrivateConversationProfile addNewPrivateConversation(String fromUsername, String targetUsername) {
        List<String> usernameList = Arrays.asList(fromUsername, targetUsername);
        List<User> fetchedUsers = userRepository.findByUsernames(usernameList);
        validateUsers(fetchedUsers,fromUsername, targetUsername);
        PrivateConversation privateConversation = new PrivateConversation(new HashSet<>(fetchedUsers));
        conversationRepository.save(privateConversation);
        return new PrivateConversationProfile(privateConversation);
    }

    @Override
    public List<BaseConversationProfile> getAllConversations(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Validator.validateValuePresent(user,"Username");
        Set<BaseConversation> conversations = user.orElse(null).getConversations();
        return conversations.stream().map(conversation -> {
            if (conversation instanceof PrivateConversation) {
                return new PrivateConversationProfile((PrivateConversation) conversation);
            } else {
                return new GroupConversationProfile((GroupConversation) conversation);
            }
        }).toList();
    }

    @Override
    public ConversationDetailsDto getConversationDetails(UUID conversationId) {
        List<Message> messages = messageRepository.getMessagesByConversationId(conversationId);
        Optional<BaseConversation> conversation = conversationRepository.findById(conversationId);
        Validator.validateValuePresent(conversation, "Conversation Id");
        return new ConversationDetailsDto(conversation.orElse(null), messages);
    }


    private void validateUsers(List<User> fetchedUsers, String fromUsername, String targetUsername) {
        Validator.validateCredentialNotEmpty(fromUsername, "from username");
        Validator.validateCredentialNotEmpty(targetUsername, "target username");
        ConversationValidator.validateNotAddingSelfChat(fromUsername, targetUsername);
        ConversationValidator.validateRequestedUsersPresent(fetchedUsers, fromUsername, targetUsername);
    }

}
