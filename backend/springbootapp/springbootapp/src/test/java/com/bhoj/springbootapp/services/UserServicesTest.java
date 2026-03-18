package com.bhoj.springbootapp.services;

import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServicesTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServices userServices;

    @Test
    void saveUser_ShouldReturnSavedUser() {

        User user = User.builder()
                .id("1")
                .firstName("test")
                .email("test@test.com")
                .build();

        when(userRepository.save(user)).thenReturn(user);

        User result = userServices.saveUser(user);

        assertNotNull(result);
        assertEquals("test@test.com", result.getEmail());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getUserById_ShouldReturnUser() {

        User user = User.builder()
                .id("1")
                .firstName("test")
                .email("test@test.com")
                .build();

        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        Optional<User> result = userServices.getUserById("1");

        assertTrue(result.isPresent());
        assertEquals("test", result.get().getFirstName());

        verify(userRepository, times(1)).findById("1");
    }
}