package com.Virtual_lab.Virtual_lab_app.websocket;

import com.Virtual_lab.Virtual_lab_app.websocket.dto.CodeChangeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CodeSyncController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/session/{sessionCode}/sync")
    public void syncCode(@DestinationVariable String sessionCode, @Payload CodeChangeEvent event) {
        messagingTemplate.convertAndSend("/topic/session/" + sessionCode, event);
    }
}
