package com.Virtual_lab.Virtual_lab_app.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}