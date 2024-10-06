package com.example.ChatApp.data.socket.TextMessage;

import com.example.ChatApp.data.socket.SocketDtoBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PublishMessageRequestDto extends SocketDtoBase {
    private  UUID tempId;
    private  String messageContent;
    private  Long conversationId;
}
