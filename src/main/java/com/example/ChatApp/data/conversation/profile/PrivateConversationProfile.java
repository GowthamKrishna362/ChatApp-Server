package com.example.ChatApp.data.conversation.profile;

import com.example.ChatApp.models.conversations.PrivateConversation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PrivateConversationProfile extends BaseConversationProfile {
    public PrivateConversationProfile(PrivateConversation conversation) {
        super(conversation);
    }
}