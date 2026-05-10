package com.Virtual_lab.Virtual_lab_app.chat.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "chat_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    @Id
    private String id;
    private String sessionCode;
    private String senderId;
    private String content;
    private Instant timestamp;
}
