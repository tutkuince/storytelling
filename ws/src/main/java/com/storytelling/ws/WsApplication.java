package com.storytelling.ws;

import com.storytelling.ws.user.User;
import com.storytelling.ws.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class WsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner userCreator(UserRepository userRepository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        List<User> users = new ArrayList<>();
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                for (int i = 1; i <= 25; i++) {
                    User user = new User();
                    user.setUsername("user" + i);
                    user.setEmail("user" + i + "@mail.com");
                    user.setPassword(passwordEncoder.encode("Password1"));
                    user.setActive(true);
                    users.add(user);
                }
                userRepository.saveAll(users);
            }
        };
    }
}
