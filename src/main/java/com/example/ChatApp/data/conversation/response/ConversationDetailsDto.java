package com.example.ChatApp.data.conversation.response;

import com.example.ChatApp.data.conversation.profile.BaseConversationProfile;
import com.example.ChatApp.data.conversation.profile.GroupConversationProfile;
import com.example.ChatApp.data.conversation.profile.PrivateConversationProfile;
import com.example.ChatApp.data.dto.MessageResponseDto;
import com.example.ChatApp.data.enums.ConversationType;
import com.example.ChatApp.data.user.profile.UserProfile;
import com.example.ChatApp.models.BaseConversation;
import com.example.ChatApp.models.GroupConversation;
import com.example.ChatApp.models.Message;
import com.example.ChatApp.models.PrivateConversation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ConversationDetailsDto {
    private BaseConversationProfile conversationMetadata;
    private List<MessageResponseDto> messages;
    private ConversationType conversationType;

    public ConversationDetailsDto(BaseConversation baseConversation, List<Message> messages) {
        this.conversationMetadata = mapToProfile(baseConversation);
        this.messages = messages.stream().map(MessageResponseDto::new).toList();
    }

    private BaseConversationProfile mapToProfile(BaseConversation conversation) {
        if(conversation instanceof PrivateConversation) {
            return new PrivateConversationProfile((PrivateConversation) conversation);
        } else {
            return new GroupConversationProfile((GroupConversation) conversation);
        }
    }
}