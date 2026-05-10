package com.Virtual_lab.Virtual_lab_app.websocket.dto;

import lombok.Data;

@Data
public class CodeChangeEvent {
    private String sessionCode;
    private String content;
    private String senderId;
}
