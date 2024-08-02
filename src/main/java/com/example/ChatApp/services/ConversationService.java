package com.example.ChatApp.services;

import com.example.ChatApp.Models.Dto.MessageResponseDto;
import com.example.ChatApp.Models.Entity.Conversation;
import com.example.ChatApp.Models.Entity.ConversationMember;
import com.example.ChatApp.Models.Entity.Message;
import com.example.ChatApp.Models.Entity.User;
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
public class ConversationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    ConversationMemberRepository conversationMemberRepository;
    @Autowired
    MessageRepository messageRepository;

    private void validateUsers(List<User> fetchedUsers, String fromUsername, String targetUsername) {
        Validator.validateCredentialNotEmpty(fromUsername, "from username");
        Validator.validateCredentialNotEmpty(targetUsername, "target username");
        ConversationValidator.validateNotAddingSelfChat(fromUsername, targetUsername);
        ConversationValidator.validateRequestedUsersPresent(fetchedUsers, fromUsername, targetUsername);
    }

    @Transactional()
    public Conversation addNewConversation(String fromUsername, String targetUsername) {
        List<String> usernameList = Arrays.asList(fromUsername, targetUsername);
        List<User> fetchedUsers = userRepository.findByUsernames(usernameList);
        validateUsers(fetchedUsers, fromUsername, targetUsername);

        Conversation conversation = new Conversation(fromUsername + "-" + targetUsername);
        Conversation savedConversation = conversationRepository.save(conversation);
        List<ConversationMember> conversationMembers = Arrays.asList(
                new ConversationMember(fromUsername, savedConversation.getId()),
                new ConversationMember(targetUsername, savedConversation.getId())
        );
        conversationMemberRepository.saveAll(conversationMembers);

        return savedConversation;
    }

    public List<Conversation> getAllConversations(String username) {
        return conversationMemberRepository.findConversationsByUsername(username);
    }

    public List<MessageResponseDto> getConversationMessages(Long conversationId) {
        List<Message> messages = messageRepository.getMessagesByConversationId(conversationId);
        return ConversationConverter.convertMessagesToDto(messages);
    }
}
