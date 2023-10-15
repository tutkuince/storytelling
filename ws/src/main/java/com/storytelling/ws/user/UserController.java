package com.storytelling.ws.user;

import com.storytelling.ws.configuration.CurrentUser;
import com.storytelling.ws.shared.Messages;
import com.storytelling.ws.user.dto.UserCreate;
import com.storytelling.ws.user.dto.UserDTO;
import com.storytelling.ws.user.dto.UserUpdate;
import com.storytelling.ws.user.exception.AuthorizationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public Page<UserDTO> finAll(Pageable pageable, @AuthenticationPrincipal CurrentUser currentUser) {
        return userService.findAll(pageable, currentUser).map(UserDTO::new);
    }

    @GetMapping("/v1/users/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO userDTO = new UserDTO(userService.findById(id));
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/v1/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id, @Valid @RequestBody UserUpdate userUpdate, @AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getId() != id) {
            throw new AuthorizationException();
        }
        UserDTO userDTO = new UserDTO(userService.updateUser(id, userUpdate));
        return ResponseEntity.ok(userDTO);
    }
}
