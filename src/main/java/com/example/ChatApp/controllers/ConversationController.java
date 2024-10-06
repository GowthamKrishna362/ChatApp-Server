package com.example.ChatApp.controllers;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.conversation.request.CreateGroupConversationRequestDto;
import com.example.ChatApp.data.conversation.response.ConversationMessageDetailsDto;
import com.example.ChatApp.data.exception.ConversationNotFoundException;
import com.example.ChatApp.data.exception.UserNotFoundException;
import com.example.ChatApp.data.socket.ConversationCreatedEventDto;
import com.example.ChatApp.data.socket.TextMessage.MessageResponseDto;
import com.example.ChatApp.models.User;
import com.example.ChatApp.services.impl.ConversationServiceImpl;
import com.example.ChatApp.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/conversation")
public class ConversationController {
    @Autowired
    private ConversationServiceImpl conversationService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/private/new/{targetUsername}")
    public PrivateConversationProfile createPrivateConversation(@PathVariable String targetUsername) throws UserNotFoundException {
        User fromUser = AuthUtil.currentUser();
        PrivateConversationProfile conversationProfile =
                conversationService.addNewPrivateConversation(fromUser.getUsername() , targetUsername);

        messagingTemplate.convertAndSend(
                "/topic/user/" + targetUsername,
                new ConversationCreatedEventDto(conversationProfile)
        );
        return conversationProfile;

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/group/new")
    public GroupConversationProfile createGroupConversation(@RequestBody CreateGroupConversationRequestDto createGroupConversationRequestDto ) throws UserNotFoundException {
        return conversationService.addNewGroupConversation(createGroupConversationRequestDto);
    }


    @GetMapping("/{username}")
    public List<BaseConversationProfile> getAllConversations(@PathVariable String username) throws UserNotFoundException {
       return conversationService.getAllConversations(username);
    }

    @GetMapping("/{conversationId}/messageDetails")
    public ConversationMessageDetailsDto getConversationMessageDetails(@PathVariable Long conversationId) throws ConversationNotFoundException {
        return conversationService.getConversationMessageDetails(conversationId);
    }

    @GetMapping("/{conversationId}/messages")
    public List<MessageResponseDto> getConversationMessages(@PathVariable Long conversationId) {
        return conversationService.getConversationMessages(conversationId);
    }

}
