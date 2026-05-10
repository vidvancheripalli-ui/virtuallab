package com.Virtual_lab.Virtual_lab_app.auth.dto;

import lombok.Data;

@Data
public class SignupRequest {

    private String username;
    private String email;
    private String password;

}