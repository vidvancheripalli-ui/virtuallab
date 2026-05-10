package com.Virtual_lab.Virtual_lab_app.websocket;

import com.Virtual_lab.Virtual_lab_app.chat.model.ChatMessage;
import com.Virtual_lab.Virtual_lab_app.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.Instant;

@Controller
@RequiredArgsConstructor
public class ChatSyncController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;

    @MessageMapping("/session/{sessionCode}/chat")
    public void sendMessage(@DestinationVariable String sessionCode, @Payload ChatMessage message) {
        message.setSessionCode(sessionCode);
        message.setTimestamp(Instant.now());
        ChatMessage saved = chatMessageRepository.save(message);
        messagingTemplate.convertAndSend("/topic/chat/" + sessionCode, saved);
    }
}
