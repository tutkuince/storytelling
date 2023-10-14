package com.storytelling.ws.auth.token;

import com.storytelling.ws.auth.dto.Credentials;
import com.storytelling.ws.user.User;
import com.storytelling.ws.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class BasicAuthTokenService implements TokenService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public BasicAuthTokenService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Token createToken(User user, Credentials credentials) {
        String emailColonPassword = credentials.email() + ":" + credentials.password();
        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());
        return new Token("Basic", token);
    }

    @Override
    public User verifyToken(String authorizationHeader) {
        if (authorizationHeader == null) return null;

        String base64Encoded = authorizationHeader.split("Basic ")[1];
        String decoded = new String(Base64.getDecoder().decode(base64Encoded));
        String[] credentials = decoded.split(":");
        String email = credentials[0];
        String password = credentials[1];
        User inDB = userService.findByEmail(email);
        if (inDB == null) return null;
        if (!passwordEncoder.matches(password, inDB.getPassword())) return null;
        return  inDB;
    }
}
