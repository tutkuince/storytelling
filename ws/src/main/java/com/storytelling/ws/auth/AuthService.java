package com.storytelling.ws.auth;

import com.storytelling.ws.auth.dto.Credentials;

public interface AuthService {
    void authenticate(Credentials credentials);
}
