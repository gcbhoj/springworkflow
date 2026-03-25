package com.bhoj.springbootapp.serviceImpl;

import com.bhoj.springbootapp.DTO.RegistrationRequest;
import com.bhoj.springbootapp.DTO.RegistrationResponse;
import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class UserServicesImplTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserServicesImpl userService;

    private RegistrationRequest regRequest;

    @BeforeEach
    void setUp() {

         regRequest = RegistrationRequest.builder()
                .firstName("Jack")
                .lastName("Dark")
                .email("jack@example.com")
                .build();

    }


    @Nested
    @DisplayName("Registration Tests")
    class RegistrationTests {
        @Test
        @DisplayName("Should Register User Successfully")
        void shouldRegisterUser() {

            // mock behavior
            when(userRepo.findByEmail(regRequest.getEmail()))
                    .thenReturn(Optional.empty());

            when(userRepo.save(any(User.class)))
                    .thenAnswer(invocation -> {
                        User user = invocation.getArgument(0);
                        user.setUserId("123");
                        return user;
                    });

            // call method
            RegistrationResponse response = userService.addNewUser(regRequest);

            // assertions
            assertNotNull(response);
            assertEquals("123", response.getUserId());
            assertEquals("USER REGISTERED SUCCESSFULLY", response.getMessage());

            verify(userRepo, times(1)).save(any(User.class));
        }

        @Test
        @DisplayName("Should throw exception when email exists")
        void shouldThrowExceptionIfEmailExists() {

            when(userRepo.findByEmail(regRequest.getEmail()))
                    .thenReturn(Optional.of(new User()));

            assertThrows(RuntimeException.class, () ->
                    userService.addNewUser(regRequest)
            );

            verify(userRepo, never()).save(any(User.class));
        }
    }


}