package com.Virtual_lab.Virtual_lab_app.queue.service;

import com.Virtual_lab.Virtual_lab_app.queue.model.QueueRequest;
import com.Virtual_lab.Virtual_lab_app.queue.repository.QueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueService {
    private final QueueRepository queueRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public QueueRequest raiseDoubt(String sessionCode, String username, String topic) {
        QueueRequest request = QueueRequest.builder()
                .sessionCode(sessionCode)
                .username(username)
                .topic(topic)
                .resolved(false)
                .createdAt(Instant.now())
                .build();
        QueueRequest saved = queueRepository.save(request);
        broadcastQueueUpdate(sessionCode);
        return saved;
    }

    public void resolveDoubt(String id) {
        queueRepository.findById(id).ifPresent(request -> {
            request.setResolved(true);
            queueRepository.save(request);
            broadcastQueueUpdate(request.getSessionCode());
        });
    }

    public List<QueueRequest> getActiveQueue(String sessionCode) {
        return queueRepository.findBySessionCodeAndResolvedFalseOrderByCreatedAtAsc(sessionCode);
    }

    private void broadcastQueueUpdate(String sessionCode) {
        List<QueueRequest> queue = getActiveQueue(sessionCode);
        messagingTemplate.convertAndSend("/topic/queue/" + sessionCode, queue);
    }
}
