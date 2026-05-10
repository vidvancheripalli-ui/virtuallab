package com.Virtual_lab.Virtual_lab_app.sessions;

import com.Virtual_lab.Virtual_lab_app.sessions.SessionModel;
import com.Virtual_lab.Virtual_lab_app.sessions.SessionRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    
    @Autowired
    private com.Virtual_lab.Virtual_lab_app.queue.repository.QueueRepository queueRepository;
    
    @Autowired
    private com.Virtual_lab.Virtual_lab_app.chat.repository.ChatMessageRepository chatMessageRepository;

    // 🔹 Create Session
    public SessionModel createSession(String userId) {

        SessionModel session = new SessionModel();

        session.setSessionId(UUID.randomUUID().toString());
        session.setMentorCode(generateCode("mnt"));
        session.setStudentCode(generateCode("stu"));
        session.setCreatedBy(userId);
        session.setCreatedAt(LocalDateTime.now());

        return sessionRepository.save(session);
    }

    // 🔹 Join Session
    public Map<String, String> joinSession(String code) {

        String role;
        SessionModel session;

        if (code.startsWith("mnt-")) {
            session = sessionRepository.findByMentorCode(code)
                    .orElseThrow(() -> new RuntimeException("Invalid mentor code"));
            role = "MENTOR";

        } else if (code.startsWith("stu-")) {
            session = sessionRepository.findByStudentCode(code)
                    .orElseThrow(() -> new RuntimeException("Invalid student code"));
            role = "STUDENT";

        } else {
            throw new RuntimeException("Invalid code format");
        }

        Map<String, String> response = new HashMap<>();
        response.put("sessionId", session.getSessionId());
        response.put("role", role);

        return response;
    }

    // 🔹 Code Generator with Prefix
    private String generateCode(String prefix) {
        String random = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        return prefix + "-" + random;
    }

    // 🔹 Analytics
    public SessionAnalyticsDTO getAnalytics(String sessionCode) {
        SessionModel session = sessionRepository.findByMentorCode(sessionCode)
                .orElseGet(() -> sessionRepository.findByStudentCode(sessionCode)
                        .orElseThrow(() -> new RuntimeException("Session not found")));
        
        long duration = java.time.Duration.between(session.getCreatedAt(), LocalDateTime.now()).toMinutes();
        int doubts = (int) queueRepository.countBySessionCode(sessionCode);
        int messages = (int) chatMessageRepository.countBySessionCode(sessionCode);

        return SessionAnalyticsDTO.builder()
                .sessionCode(sessionCode)
                .durationMinutes(duration)
                .totalDoubts(doubts)
                .totalMessages(messages)
                .createdBy(session.getCreatedBy())
                .build();
    }
}