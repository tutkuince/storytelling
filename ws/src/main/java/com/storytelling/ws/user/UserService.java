package com.storytelling.ws.user;

import java.util.List;

public interface UserService {
    void createUser(User user);
    String encodePassword(String password);
    User findByEmail(String email);
    void activateUser(String token);
    List<User> findAll();
}
