package com.storytelling.ws.user;

import com.storytelling.ws.configuration.CurrentUser;
import com.storytelling.ws.user.dto.UserUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    void createUser(User user);
    String encodePassword(String password);
    User findByEmail(String email);
    void activateUser(String token);
    Page<User> findAll(Pageable pageable, CurrentUser currentUser);
    User findById(Long id);
    User updateUser(long id, UserUpdate userUpdate);
}
