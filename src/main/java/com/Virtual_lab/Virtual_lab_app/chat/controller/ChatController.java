package com.Virtual_lab.Virtual_lab_app.chat.controller;

import com.Virtual_lab.Virtual_lab_app.chat.model.ChatMessage;
import com.Virtual_lab.Virtual_lab_app.chat.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/session/{sessionCode}")
    public List<ChatMessage> getChatHistory(@PathVariable String sessionCode) {
        return chatMessageRepository.findBySessionCodeOrderByTimestampAsc(sessionCode);
    }
}
