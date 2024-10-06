package com.example.ChatApp.models;

import com.example.ChatApp.data.socket.TextMessage.PublishMessageRequestDto;
import com.example.ChatApp.models.conversations.BaseConversation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Messages")
public class Message extends ClientTimestampBaseEntity {
    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "conversationId")
    private BaseConversation conversation;

    private String messageContent;

    public Message(User sender, BaseConversation conversation, PublishMessageRequestDto publishMessageRequestDto) {
        super(publishMessageRequestDto.getTimeStamp());
        this.sender = sender;
        this.conversation =conversation;
        this.messageContent = publishMessageRequestDto.getMessageContent();
    }

}
