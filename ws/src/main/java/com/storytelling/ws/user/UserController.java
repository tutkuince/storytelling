package com.storytelling.ws.user;

import com.storytelling.ws.error.ApiError;
import com.storytelling.ws.shared.GenericMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        Map<String, String> validationErrors = new HashMap<>();
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage("Validation Error");
        apiError.setStatus(400);

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            validationErrors.put("username", "Username cannot be null!");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            validationErrors.put("email", "E-mail cannot be null!");
        }
        if (!validationErrors.isEmpty()) {
            apiError.setValidationErrors(validationErrors);
            return ResponseEntity.badRequest().body(apiError);
        }

        userService.createUser(user);
        return ResponseEntity.ok(new GenericMessage("User is created"));
    }
}
