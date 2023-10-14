package com.storytelling.ws.user.exception;

public class AuthorizationException extends RuntimeException {
    public AuthorizationException() {
        super("Forbidden");
    }
}
