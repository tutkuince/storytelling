package com.storytelling.ws.auth;

import com.storytelling.ws.auth.dto.Credentials;
import com.storytelling.ws.auth.exception.AuthenticationException;
import com.storytelling.ws.user.User;
import com.storytelling.ws.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private UserService userService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void authenticate(Credentials credentials) {
        User inDB = userService.findByEmail(credentials.email());
        if (inDB == null) throw new AuthenticationException();
        if (!passwordEncoder.matches(credentials.password(), inDB.getPassword())) throw new AuthenticationException();
    }
}
