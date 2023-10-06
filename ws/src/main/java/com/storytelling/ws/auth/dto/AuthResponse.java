package com.storytelling.ws.auth.dto;

import com.storytelling.ws.auth.token.Token;
import com.storytelling.ws.user.dto.UserDTO;

public class AuthResponse {

    private UserDTO user;
    private Token token;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
