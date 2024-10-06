package com.example.ChatApp.data.socket.VideoConversation;

import com.example.ChatApp.data.socket.SocketDtoBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoCallDto extends SocketDtoBase {
    private String sender;
    private String recipient;
}
