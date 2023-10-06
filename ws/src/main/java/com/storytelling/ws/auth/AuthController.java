package com.storytelling.ws.auth;

import com.storytelling.ws.auth.dto.AuthResponse;
import com.storytelling.ws.auth.dto.Credentials;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> handleAuthentication(@Valid @RequestBody Credentials credential) {
        AuthResponse authResponse = authService.authenticate(credential);
        return ResponseEntity.ok().body(authResponse);
    }
}
