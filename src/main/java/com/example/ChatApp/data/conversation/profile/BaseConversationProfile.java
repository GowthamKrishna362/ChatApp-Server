package com.example.ChatApp.data.conversation.profile;

import com.example.ChatApp.data.enums.ConversationType;
import com.example.ChatApp.data.user.profile.UserProfile;
import com.example.ChatApp.models.conversations.BaseConversation;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class BaseConversationProfile {
    private String id;
    private List<UserProfile> members;
    private ConversationType conversationType;

    public BaseConversationProfile(BaseConversation conversation) {
        this.id = conversation.getId().toString();
        this.conversationType = conversation.getConversationType();
        this.members = conversation.getMembers().stream().map(UserProfile::new).collect(Collectors.toList());
    }
}
