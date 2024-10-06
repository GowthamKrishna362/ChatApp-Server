package com.example.ChatApp.data.socket;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.enums.SocketMessageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversationCreatedEventDto extends SocketDtoBase {

    private BaseConversationProfile conversationProfile;

    public ConversationCreatedEventDto(BaseConversationProfile conversationProfile) {
        super(SocketMessageType.CONVERSATION_CREATED, null);
        this.conversationProfile = conversationProfile;
    }
}
