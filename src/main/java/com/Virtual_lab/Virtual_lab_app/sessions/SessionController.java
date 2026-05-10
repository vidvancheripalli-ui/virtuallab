package com.Virtual_lab.Virtual_lab_app.sessions;

import com.Virtual_lab.Virtual_lab_app.sessions.SessionModel;
import com.Virtual_lab.Virtual_lab_app.sessions.SessionService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    // 🔹 Create Session
    @PostMapping("/create")
    public SessionModel createSession(@RequestParam String userId) {
        return sessionService.createSession(userId);
    }

    // 🔹 Join Session
    @PostMapping("/join")
    public Map<String, String> joinSession(@RequestParam String code) {
        return sessionService.joinSession(code);
    }

    // 🔹 Analytics
    @GetMapping("/{code}/analytics")
    public SessionAnalyticsDTO getAnalytics(@PathVariable String code) {
        return sessionService.getAnalytics(code);
    }
}