package com.example.ChatApp.Services;

import com.example.ChatApp.Models.Entity.Conversation;
import com.example.ChatApp.Models.Entity.ConversationMember;
import com.example.ChatApp.Models.Entity.User;
import com.example.ChatApp.Repository.ConversationMemberRepository;
import com.example.ChatApp.Repository.ConversationRepository;
import com.example.ChatApp.Repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
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

    public void validateUsers(List<User> fetchedUsers, String fromUsername, String targetUsername) {
        if(fromUsername.equals(targetUsername)) {
            throw new ValidationException("Cant add chat with yourself");
        }
        if (fetchedUsers.isEmpty()) {
            throw new ValidationException(
                    "Usernames " + fromUsername + " and " + targetUsername + " are invalid"
            );
        }
        if (fetchedUsers.size() == 1) {
            String invalidUsername = fetchedUsers.getFirst().getUsername().equals(fromUsername) ? targetUsername : fromUsername;
            throw new ValidationException("Username " + invalidUsername + " is invalid");
        }
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
}
