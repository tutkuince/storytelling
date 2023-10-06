package com.storytelling.ws.auth.token;

public record Token(
        String prefix,
        String token
) {
}
