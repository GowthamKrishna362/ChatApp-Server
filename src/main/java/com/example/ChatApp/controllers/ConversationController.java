package com.example.ChatApp.controllers;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.conversation.request.CreateGroupConversationRequestDto;
import com.example.ChatApp.data.conversation.response.ConversationMessageDetailsDto;
import com.example.ChatApp.data.conversation.request.CreateConversationRequestDto;
import com.example.ChatApp.data.socket.MessageResponseDto;
import com.example.ChatApp.services.impl.ConversationServiceImpl;
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
    @PostMapping("/private/new")
    public PrivateConversationProfile createPrivateConversation(@RequestBody CreateConversationRequestDto createConversationRequestDto) {
        String fromUsername = createConversationRequestDto.fromUsername();
        String targetUsername = createConversationRequestDto.targetUsername();
        return conversationService.addNewPrivateConversation(fromUsername, targetUsername);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/group/new")
    public GroupConversationProfile createGroupConversation(@RequestBody CreateGroupConversationRequestDto createGroupConversationRequestDto) {
        return conversationService.addNewGroupConversation(
                createGroupConversationRequestDto.fromUsername(),
                createGroupConversationRequestDto.targetUsernames(),
                createGroupConversationRequestDto.conversationName());
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
