package com.example.ChatApp.Controllers;

import com.example.ChatApp.Models.Entity.Message;
import com.example.ChatApp.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("new")
    public Message newMessage(@RequestBody Message message) {
        return messageService.addNewMessage(message);
    }
}
