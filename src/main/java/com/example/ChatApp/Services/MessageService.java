package com.example.ChatApp.Services;

import com.example.ChatApp.Models.Entity.Message;
import com.example.ChatApp.Repository.MessageRepository;
import com.example.ChatApp.Utils.StringUtils;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;


    public Message addNewMessage(Message message) {
        if(StringUtils.isNullOrBlank(message.getMessageContent())) {
            throw new ValidationException("Message is empty");
        }
        return messageRepository.save(message);
    }
}
