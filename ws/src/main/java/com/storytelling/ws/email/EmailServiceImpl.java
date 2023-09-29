package com.storytelling.ws.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    JavaMailSenderImpl mailSender;
    public EmailServiceImpl() {
        this.initialize();
    }

    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.ethereal.email");
        mailSender.setPort(587);
        mailSender.setUsername("claudia.durgan69@ethereal.email");
        mailSender.setPassword("khRpwvabdgqzjg8nNy");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);
    }

    @Override
    public void sendActivationEmail(String email, String activationToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("info@storytelling.com");
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText("http://localhost:5173/activation/" + activationToken);
        this.mailSender.send(message);
    }
}
