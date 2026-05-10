package com.Virtual_lab.Virtual_lab_app.sessions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionAnalyticsDTO {
    private String sessionCode;
    private long durationMinutes;
    private int totalDoubts;
    private int totalMessages;
    private String createdBy;
}
