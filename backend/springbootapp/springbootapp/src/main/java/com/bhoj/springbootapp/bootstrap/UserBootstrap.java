package com.bhoj.springbootapp.bootstrap;

import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserBootstrap implements CommandLineRunner {

    private final UserRepository userRepo;
    @Override
    public void run(String... args) throws Exception {

        User user = User.builder()
                .firstName("John")
                .email("john@example.com")
                .build();

        userRepo.save(user);



    }
}
