package com.Virtual_lab.Virtual_lab_app.queue.repository;

import com.Virtual_lab.Virtual_lab_app.queue.model.QueueRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface QueueRepository extends MongoRepository<QueueRequest, String> {
    List<QueueRequest> findBySessionCodeAndResolvedFalseOrderByCreatedAtAsc(String sessionCode);
    long countBySessionCode(String sessionCode);
}
