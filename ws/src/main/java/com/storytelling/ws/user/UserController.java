package com.storytelling.ws.user;

import com.storytelling.ws.shared.Messages;
import com.storytelling.ws.user.dto.UserCreate;
import com.storytelling.ws.user.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/v1/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreate user) {
        userService.createUser(user.toUser());
        String message = Messages.getMessageForLocale(
                "storytelling.constraints.user.success.message",
                LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/v1/users/{token}/active")
    public ResponseEntity<?> activateUser(@PathVariable String token) {
        userService.activateUser(token);
        String message = Messages.getMessageForLocale("storytelling.activate.user.success.message", LocaleContextHolder.getLocale());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/v1/users")
    public Page<UserDTO> finAll(Pageable pageable) {
        return userService.findAll(pageable).map(UserDTO::new);
    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO userDTO = new UserDTO(userService.findById(id));
        return ResponseEntity.ok(userDTO);
    }
}
