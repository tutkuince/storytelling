package com.storytelling.ws.email;

import com.storytelling.ws.configuration.StorytellingProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSenderImpl mailSender;
    private StorytellingProperties properties;

    @Autowired
    public void setProperties(StorytellingProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void initialize() {
        this.mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getEmail().host());
        mailSender.setPort(properties.getEmail().port());
        mailSender.setUsername(properties.getEmail().username());
        mailSender.setPassword(properties.getEmail().password());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.starttls.enable", true);
    }

    @Override
    public void sendActivationEmail(String email, String activationToken) {
        String activationUrl = properties.getClient().host() + "/activation/" + activationToken;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getEmail().from());
        message.setTo(email);
        message.setSubject("Account Activation");
        message.setText(activationUrl);
        this.mailSender.send(message);
    }
}
