package com.bhoj.springbootapp.bootstrap;

import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class UserBootstrap implements CommandLineRunner {

    private final UserRepository userRepo;
    @Override
    public void run(String... args) throws Exception {

        User user = User.builder()
                .userId("11121314-1516-1718-1920-212223242526")
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        userRepo.save(user);



    }
}
