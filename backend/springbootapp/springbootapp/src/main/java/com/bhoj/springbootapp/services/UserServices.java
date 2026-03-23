package com.bhoj.springbootapp.services;

import com.bhoj.springbootapp.DTO.RegistrationRequest;
import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepo;

    public User saveUser(RegistrationRequest request) {

        User user = User.builder()
                .firstName(request.getFirstName())
                .email(request.getEmail())
                .build();

        return userRepo.save(user);
    }

    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }
}
