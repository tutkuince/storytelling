package com.storytelling.ws.user;

public interface UserService {

    void createUser(User user);
    String encodePassword(String password);
}
