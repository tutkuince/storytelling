package com.storytelling.ws.user;

import com.storytelling.ws.email.EmailService;
import com.storytelling.ws.user.exception.ActivationNotificationException;
import com.storytelling.ws.user.exception.InvalidTokenException;
import com.storytelling.ws.user.exception.NotUniqueEmailException;
import com.storytelling.ws.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(rollbackFor = MailException.class)
    @Override
    public void createUser(User user) {
        try {
            String encodedPassword = encodePassword(user.getPassword());
            user.setPassword(encodedPassword);
            user.setActivationToken(UUID.randomUUID().toString());
            // Solving Post-Transactional Problems with saveAndFlush
            userRepository.saveAndFlush(user);
            emailService.sendActivationEmail(user.getEmail(), user.getActivationToken());
        } catch (DataIntegrityViolationException exception) {
            throw new NotUniqueEmailException();
        } catch (MailException exception) {
            throw new ActivationNotificationException();
        }
    }

    @Override
    public String encodePassword(String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void activateUser(String token) {
        User user = userRepository.findByActivationToken(token);
        if (user == null) {
            throw new InvalidTokenException();
        }
        user.setActive(true);
        user.setActivationToken(null);
        userRepository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UserNotFoundException();
        }
    }
}
