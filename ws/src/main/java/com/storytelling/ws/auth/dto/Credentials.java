package com.storytelling.ws.auth.dto;

public record Credentials(
        String email,
        String password
) {
}
