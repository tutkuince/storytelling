package com.storytelling.ws.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record UserUpdate(@Column(name = "username", unique = true)
                         @NotBlank(message = "{storytelling.constraints.username.notblank}")
                         String username) {
}
