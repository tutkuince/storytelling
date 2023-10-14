package com.storytelling.ws.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void createUser(User user);
    String encodePassword(String password);
    User findByEmail(String email);
    void activateUser(String token);
    Page<User> findAll(Pageable pageable, User loggedInUser);
    User findById(Long id);
}
