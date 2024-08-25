package com.example.ChatApp.services.impl;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.conversation.request.CreateConversationRequestDto;
import com.example.ChatApp.data.conversation.request.CreateGroupConversationRequestDto;
import com.example.ChatApp.data.conversation.response.ConversationMessageDetailsDto;
import com.example.ChatApp.data.socket.MessageResponseDto;
import com.example.ChatApp.models.*;
import com.example.ChatApp.models.conversations.BaseConversation;
import com.example.ChatApp.models.conversations.GroupConversation;
import com.example.ChatApp.models.conversations.PrivateConversation;
import com.example.ChatApp.repository.ConversationRepository;
import com.example.ChatApp.repository.MessageRepository;
import com.example.ChatApp.repository.UserRepository;
import com.example.ChatApp.services.ConversationService;
import com.example.ChatApp.services.mappers.ConversationMapper;
import com.example.ChatApp.services.mappers.MessageMapper;
import com.example.ChatApp.utils.validators.ConversationValidator;
import com.example.ChatApp.utils.validators.Validator;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
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
    public PrivateConversationProfile  addNewPrivateConversation(String fromUsername, String targetUsername) {
        List<String> usernameList = Arrays.asList(fromUsername, targetUsername);
        List<User> fetchedUsers = userRepository.findByUsernames(usernameList);
        validateUsers(fetchedUsers, fromUsername, targetUsername);
        PrivateConversation privateConversation = new PrivateConversation(new HashSet<>(fetchedUsers));
        conversationRepository.save(privateConversation);
        return new PrivateConversationProfile(privateConversation);
    }

    //    TODO Validate users exist
    @Override
    public GroupConversationProfile addNewGroupConversation(CreateGroupConversationRequestDto createGroupConversationRequestDto) {
        String fromUsername = createGroupConversationRequestDto.fromUsername();
        List<String> targetUsernames = createGroupConversationRequestDto.targetUsernames();
        String conversationName = createGroupConversationRequestDto.conversationName();
        User creator = userRepository.findByUsername(fromUsername).orElse(null);
        List<User> targetUsers = userRepository.findByUsernames(targetUsernames);
        List<User> allMembers = new ArrayList<>(targetUsers);
        allMembers.add(creator);
        GroupConversation groupConversation = new GroupConversation(
                new HashSet<>(Collections.singleton(creator)),
                new HashSet<>(allMembers),
                conversationName);
        conversationRepository.save(groupConversation);
        return new GroupConversationProfile(groupConversation);
    }

    @Override
    public List<BaseConversationProfile> getAllConversations(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        Validator.validateValuePresent(user, "Username");
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
    public ConversationMessageDetailsDto getConversationMessageDetails(Long conversationId) {
        BaseConversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ValidationException("Conversation does not exist"));
        return new ConversationMessageDetailsDto(
                MessageMapper.toMessageResponseDtoList(conversation.getMessages()),
                ConversationMapper.toConversationOpenEventDtoList(conversation.getConversationOpenEvents()));
    }

    public List<MessageResponseDto> getConversationMessages(Long conversationId) {
        List<Message> messages = messageRepository.getMessagesByConversationId(conversationId);
        return MessageMapper.toMessageResponseDtoList(messages);
    }


    private void validateUsers(List<User> fetchedUsers, String fromUsername, String targetUsername) {
        Validator.validateCredentialNotEmpty(fromUsername, "from username");
        Validator.validateCredentialNotEmpty(targetUsername, "target username");
        ConversationValidator.validateNotAddingSelfChat(fromUsername, targetUsername);
        ConversationValidator.validateRequestedUsersPresent(fetchedUsers, fromUsername, targetUsername);
    }


}
