package com.example.ChatApp.controllers;

import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.dto.ConversationDetailsDto;
import com.example.ChatApp.data.conversation.request.CreateConversationRequestDto;
import com.example.ChatApp.services.impl.ConversationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/conversation")
public class ConversationController {
    @Autowired
    ConversationServiceImpl conversationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public PrivateConversationProfile createConversation(@RequestBody CreateConversationRequestDto createConversationRequestDto) {
        String fromUsername = createConversationRequestDto.fromUsername();
        String targetUsername = createConversationRequestDto.targetUsername();
        return conversationService.addNewPrivateConversation(fromUsername, targetUsername);
    }

    @GetMapping("/{username}")
    public List<PrivateConversationProfile> getAllConversations(@PathVariable String username) {
       return conversationService.getAllConversations(username);
    }

    @GetMapping("/{conversationId}/details")
    public ConversationDetailsDto getConversationDetails(@PathVariable String conversationId) {
        return conversationService.getConversationDetails(UUID.fromString(conversationId));
    }
}
