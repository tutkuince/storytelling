package com.storytelling.ws.email;

import com.storytelling.ws.user.User;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {

    void sendActivationEmail(String email, String activationToken);

}
