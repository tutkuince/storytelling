package com.storytelling.ws.auth;

import com.storytelling.ws.auth.dto.AuthResponse;
import com.storytelling.ws.auth.dto.Credentials;

public interface AuthService {
    AuthResponse authenticate(Credentials credentials);
}
