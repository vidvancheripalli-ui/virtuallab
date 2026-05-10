package com.Virtual_lab.Virtual_lab_app.auth.contollers;

import com.Virtual_lab.Virtual_lab_app.auth.dto.LoginRequest;
import com.Virtual_lab.Virtual_lab_app.auth.dto.LoginResponse;
import com.Virtual_lab.Virtual_lab_app.auth.dto.SignupRequest;
import com.Virtual_lab.Virtual_lab_app.auth.service.AuthService;
import com.Virtual_lab.Virtual_lab_app.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        return new LoginResponse(token);
    }
}