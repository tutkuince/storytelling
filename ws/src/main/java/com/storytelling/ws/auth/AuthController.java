package com.storytelling.ws.auth;

import com.storytelling.ws.auth.dto.Credentials;
import com.storytelling.ws.auth.exception.AuthenticationException;
import com.storytelling.ws.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/auth")
    public void handleAuthentication(@RequestBody Credentials credential) {
        authService.authenticate(credential);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiError> handleAuthenticationEx(AuthenticationException exception, HttpServletRequest request) {
        ApiError apiError = new ApiError();
        apiError.setPath(request.getRequestURI());
        apiError.setStatus(401);
        apiError.setMessage(exception.getMessage());
        return ResponseEntity.status(401).body(apiError);
    }
}
