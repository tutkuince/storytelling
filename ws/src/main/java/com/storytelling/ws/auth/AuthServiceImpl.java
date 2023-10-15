package com.storytelling.ws.auth;

import com.storytelling.ws.auth.dto.AuthResponse;
import com.storytelling.ws.auth.dto.Credentials;
import com.storytelling.ws.auth.exception.AuthenticationException;
import com.storytelling.ws.auth.token.Token;
import com.storytelling.ws.auth.token.TokenService;
import com.storytelling.ws.user.User;
import com.storytelling.ws.user.UserService;
import com.storytelling.ws.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final TokenService tokenService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponse authenticate(Credentials credentials) {
        User inDB = userService.findByEmail(credentials.email());
        if (inDB == null) throw new AuthenticationException();
        if (!passwordEncoder.matches(credentials.password(), inDB.getPassword())) throw new AuthenticationException();

        Token token = tokenService.createToken(inDB, credentials);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(new UserDTO(inDB));
        authResponse.setToken(token);

        return authResponse;
    }
}
