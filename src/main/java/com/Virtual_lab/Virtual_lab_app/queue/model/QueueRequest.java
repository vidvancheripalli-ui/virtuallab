package com.Virtual_lab.Virtual_lab_app.queue.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "queues")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueueRequest {
    @Id
    private String id;
    private String sessionCode;
    private String username;
    private String topic;
    private boolean resolved;
    private Instant createdAt;
}
