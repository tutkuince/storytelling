package com.storytelling.ws.auth.token;

import com.storytelling.ws.auth.dto.Credentials;
import com.storytelling.ws.user.User;

public interface TokenService {

    Token createToken(User user, Credentials credentials);

    User verifyToken(String authorizationHeader);
}
