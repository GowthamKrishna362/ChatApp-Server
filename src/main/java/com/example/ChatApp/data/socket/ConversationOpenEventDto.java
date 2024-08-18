package com.example.ChatApp.data.socket;

import com.example.ChatApp.data.enums.SocketMessageType;
import com.example.ChatApp.models.ConversationOpenEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationOpenEventDto extends SocketDtoBase {
    private String username;
    private Long conversationId;

    public ConversationOpenEventDto(ConversationOpenEvent conversationOpenEvent) {
        super(SocketMessageType.CONVERSATION_OPEN,conversationOpenEvent.getLastOpenedAt());
        this.username = conversationOpenEvent.getUser().getUsername();
        this.conversationId = conversationOpenEvent.getConversation().getId();
    }
}
