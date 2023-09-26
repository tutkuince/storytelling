package com.storytelling.ws.user.dto;

import com.storytelling.ws.user.User;
import com.storytelling.ws.user.validation.UniqueEmail;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(
        @Column(name = "username", unique = true)
        @NotBlank(message = "{storytelling.constraints.username.notblank}")
        String username,
        @Column(name = "email")
        @NotBlank
        @Email
        @UniqueEmail
        String email,
        @Column(name = "password")
        @NotBlank
        @Size(min = 8, max = 255)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{storytelling.constraints.password.pattern}")
        String password
) {
    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}
