package com.example.ChatApp.data.socket.TextMessage;

import com.example.ChatApp.data.enums.SocketMessageType;
import com.example.ChatApp.data.socket.SocketDtoBase;
import com.example.ChatApp.models.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
public class MessageResponseDto extends SocketDtoBase {
    private UUID tempId;
    private final String id;
    private final String conversationId;
    private final String sender;
    private final String messageContent;

    public MessageResponseDto(Message message) {
        super(SocketMessageType.CHAT_MESSAGE, message.getCreatedAt());
        this.id = message.getId().toString();
        this.conversationId = message.getConversation().getId().toString();
        this.sender = message.getSender().getUsername();
        this.messageContent = message.getMessageContent();
    }
}
