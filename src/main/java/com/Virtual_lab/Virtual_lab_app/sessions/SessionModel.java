
package com.Virtual_lab.Virtual_lab_app.sessions;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "sessions")
public class SessionModel {

    @Id
    private String sessionId;

    private String mentorCode;
    private String studentCode;

    private String createdBy;
    private LocalDateTime createdAt;

    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getMentorCode() {
        return mentorCode;
    }
    public void setMentorCode(String mentorCode) {
        this.mentorCode = mentorCode;
    }
    public String getStudentCode() {
        return studentCode;
    }
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}