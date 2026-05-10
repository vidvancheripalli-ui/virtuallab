package com.Virtual_lab.Virtual_lab_app.queue.controller;

import com.Virtual_lab.Virtual_lab_app.queue.model.QueueRequest;
import com.Virtual_lab.Virtual_lab_app.queue.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/queue")
@RequiredArgsConstructor
public class QueueController {
    private final QueueService queueService;

    @PostMapping("/request")
    public QueueRequest raiseRequest(@RequestBody Map<String, String> payload) {
        return queueService.raiseDoubt(
                payload.get("sessionCode"), 
                payload.get("username"), 
                payload.get("topic")
        );
    }

    @PutMapping("/resolve/{id}")
    public void resolveRequest(@PathVariable String id) {
        queueService.resolveDoubt(id);
    }

    @GetMapping("/session/{sessionCode}")
    public List<QueueRequest> getQueue(@PathVariable String sessionCode) {
        return queueService.getActiveQueue(sessionCode);
    }
}
