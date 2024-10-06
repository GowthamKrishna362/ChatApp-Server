package com.example.ChatApp.services.impl;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.conversation.request.CreateGroupConversationRequestDto;
import com.example.ChatApp.data.conversation.response.ConversationMessageDetailsDto;
import com.example.ChatApp.data.exception.ConversationNotFoundException;
import com.example.ChatApp.data.exception.UserNotFoundException;
import com.example.ChatApp.data.socket.TextMessage.MessageResponseDto;
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
    //    TODO Validate users exist, check if findByUsername for creator is needed, get from AuthUtil - might be outdated?
    public PrivateConversationProfile  addNewPrivateConversation(String fromUsername, String targetUsername) throws UserNotFoundException {
        Validator.validateCredentialNotEmpty(fromUsername, "from username");
        Validator.validateCredentialNotEmpty(targetUsername, "target username");
        ConversationValidator.validateNotAddingSelfChat(fromUsername, targetUsername);
        User creator = userRepository.findByUsername(fromUsername).orElseThrow(() -> new UserNotFoundException(fromUsername));
        User target = userRepository.findByUsername(targetUsername).orElseThrow(() -> new UserNotFoundException(targetUsername));
        PrivateConversation privateConversation = new PrivateConversation(new HashSet<>(Arrays.asList(creator, target)));
        conversationRepository.save(privateConversation);
        return new PrivateConversationProfile(privateConversation);
    }

    //    TODO Validate users exist, check if findByUsername for creator is needed, get from AuthUtil - might be outdated?
    @Override
    public GroupConversationProfile addNewGroupConversation(CreateGroupConversationRequestDto createGroupConversationRequestDto) throws UserNotFoundException {
        String fromUsername = createGroupConversationRequestDto.fromUsername();
        List<String> targetUsernames = createGroupConversationRequestDto.targetUsernames();
        String conversationName = createGroupConversationRequestDto.conversationName();
        User creator = userRepository.findByUsername(fromUsername).orElseThrow(() ->new UserNotFoundException(fromUsername));
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
    public List<BaseConversationProfile> getAllConversations(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        Set<BaseConversation> conversations = user.getConversations();
        return conversations.stream().map(conversation -> {
            if (conversation instanceof PrivateConversation) {
                return new PrivateConversationProfile((PrivateConversation) conversation);
            } else {
                return new GroupConversationProfile((GroupConversation) conversation);
            }
        }).toList();
    }

    @Override
    public ConversationMessageDetailsDto getConversationMessageDetails(Long conversationId) throws ConversationNotFoundException {
        BaseConversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ConversationNotFoundException(conversationId));
        return new ConversationMessageDetailsDto(
                MessageMapper.toMessageResponseDtoList(conversation.getMessages()),
                ConversationMapper.toConversationOpenEventDtoList(conversation.getConversationOpenEvents()));
    }

    public List<MessageResponseDto> getConversationMessages(Long conversationId) {
        List<Message> messages = messageRepository.getMessagesByConversationId(conversationId);
        return MessageMapper.toMessageResponseDtoList(messages);
    }


    private void validateUsers(List<User> fetchedUsers, String fromUsername, String targetUsername) {
        ConversationValidator.validateRequestedUsersPresent(fetchedUsers, fromUsername, targetUsername);
    }


}
