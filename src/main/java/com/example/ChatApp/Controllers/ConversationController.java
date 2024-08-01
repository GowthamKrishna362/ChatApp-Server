package com.example.ChatApp.Controllers;

import com.example.ChatApp.Models.Dto.AddConversationRequestDto;
import com.example.ChatApp.Models.Entity.Conversation;
import com.example.ChatApp.Services.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Conversation createConversation(@RequestBody AddConversationRequestDto newConversationRequestDto) {
        String fromUsername = newConversationRequestDto.fromUsername();
        String targetUsername = newConversationRequestDto.targetUsername();
        return conversationService.addNewConversation(fromUsername, targetUsername);
    }

    @GetMapping("/{username}")
    public List<Conversation> getAllConversations(@PathVariable String username) {
       return  conversationService.getAllConversations(username);
    }
}
