package com.Virtual_lab.Virtual_lab_app.chat.repository;

import com.Virtual_lab.Virtual_lab_app.chat.model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    List<ChatMessage> findBySessionCodeOrderByTimestampAsc(String sessionCode);
    long countBySessionCode(String sessionCode);
}
