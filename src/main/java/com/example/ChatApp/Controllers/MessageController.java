package com.example.ChatApp.Controllers;

import com.example.ChatApp.Models.Dto.MessageRequestDto;
import com.example.ChatApp.Models.Entity.Message;
import com.example.ChatApp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("new")
    public Message newMessage(@RequestBody MessageRequestDto messageRequestDto) {
        return messageService.addNewMessage(messageRequestDto);
    }

}
