package com.storytelling.ws.user;

import com.storytelling.ws.user.exception.NotUniqueEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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
            sendActivationEmail(user);
        } catch (DataIntegrityViolationException exception) {
            throw new NotUniqueEmailException();
        }
    }

    private void sendActivationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@storytelling.com");
        message.setTo(user.getEmail());
        message.setSubject("Account Activation");
        message.setText("http://localhost:5173/activation/" + user.getActivationToken());
        getJavaMailSender().send(message);
    }

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.ethereal.email");
        mailSender.setPort(587);
        mailSender.setUsername("claudia.durgan69@ethereal.email");
        mailSender.setPassword("khRpwvabdgqzjg8nNy");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);

        return mailSender;
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
}
