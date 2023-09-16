package com.storytelling.ws.user;

import com.storytelling.ws.shared.GenericMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/users")
    public GenericMessage createUser(@RequestBody User user) {
        userService.createUser(user);
        return new GenericMessage("User is created");
    }
}
