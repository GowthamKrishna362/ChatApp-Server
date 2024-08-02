package com.example.ChatApp.Controllers;

import com.example.ChatApp.Models.Dto.ConversationRequestDto;
import com.example.ChatApp.Models.Dto.MessageResponseDto;
import com.example.ChatApp.Models.Entity.Conversation;
import com.example.ChatApp.services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/conversation")
public class ConversationController {
    @Autowired
    ConversationService conversationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/new")
    public Conversation createConversation(@RequestBody ConversationRequestDto newConversationRequestDto) {
        String fromUsername = newConversationRequestDto.fromUsername();
        String targetUsername = newConversationRequestDto.targetUsername();
        return conversationService.addNewConversation(fromUsername, targetUsername);
    }

    @GetMapping("/{username}")
    public List<Conversation> getAllConversations(@PathVariable String username) {
       return  conversationService.getAllConversations(username);
    }

    @GetMapping("/{conversationId}/messages")
    public List<MessageResponseDto> getConversationMessages(@PathVariable Long conversationId) {
        return conversationService.getConversationMessages(conversationId);
    }
}
