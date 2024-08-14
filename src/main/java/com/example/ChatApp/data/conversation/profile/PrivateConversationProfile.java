package com.example.ChatApp.data.conversation.profile;

import com.example.ChatApp.data.user.profile.UserProfile;
import com.example.ChatApp.models.Entity.PrivateConversation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class ConversationProfile extends BaseConversationProfile {
    public ConversationProfile(PrivateConversation conversation) {
        super(conversation);
    }
}
