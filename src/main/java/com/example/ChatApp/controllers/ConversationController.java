package com.example.ChatApp.controllers;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.conversation.request.CreateGroupConversationRequestDto;
import com.example.ChatApp.data.conversation.response.ConversationMessageDetailsDto;
import com.example.ChatApp.data.conversation.request.CreateConversationRequestDto;
import com.example.ChatApp.data.socket.MessageResponseDto;
import com.example.ChatApp.models.User;
import com.example.ChatApp.services.impl.ConversationServiceImpl;
import com.example.ChatApp.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/conversation")
public class ConversationController {
    @Autowired
    ConversationServiceImpl conversationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/private/new/{targetUsername}")
    public PrivateConversationProfile createPrivateConversation(@PathVariable String targetUsername) {
        User fromUser = AuthUtil.currentUser();
        return conversationService.addNewPrivateConversation(fromUser.getUsername() , targetUsername);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/group/new")
    public GroupConversationProfile createGroupConversation(@RequestBody CreateGroupConversationRequestDto createGroupConversationRequestDto) {
        return conversationService.addNewGroupConversation(createGroupConversationRequestDto);
    }


    @GetMapping("/{username}")
    public List<BaseConversationProfile> getAllConversations(@PathVariable String username) {
       return conversationService.getAllConversations(username);
    }

    @GetMapping("/{conversationId}/messageDetails")
    public ConversationMessageDetailsDto getConversationMessageDetails(@PathVariable Long conversationId) {
        return conversationService.getConversationMessageDetails(conversationId);
    }

    @GetMapping("/{conversationId}/messages")
    public List<MessageResponseDto> getConversationMessages(@PathVariable Long conversationId) {
        return conversationService.getConversationMessages(conversationId);
    }

}
