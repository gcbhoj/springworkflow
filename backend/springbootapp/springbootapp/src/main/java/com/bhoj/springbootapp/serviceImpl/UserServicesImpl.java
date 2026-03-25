package com.bhoj.springbootapp.serviceImpl;

import com.bhoj.springbootapp.DTO.RegistrationRequest;
import com.bhoj.springbootapp.DTO.RegistrationResponse;
import com.bhoj.springbootapp.DTO.UserProfile;
import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.exceptionHandler.BadRequestException;
import com.bhoj.springbootapp.exceptionHandler.UserCreationException;
import com.bhoj.springbootapp.repository.UserRepository;
import com.bhoj.springbootapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.util.ClassUtils.ifPresent;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserService {

    private final UserRepository userRepo;

    @Override
    public RegistrationResponse addNewUser(RegistrationRequest request) {

    userRepo.findByEmail(request.getEmail())
            .ifPresent(user->{
                throw new UserCreationException(
                        "EMAIL ALREADY EXISTS"+
                                request.getEmail());
            });

    User user  = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .build();

    User savedUser = userRepo.save(user);


        return  RegistrationResponse
                .builder()
                .userId(savedUser.getUserId())
                .message("USER REGISTERED SUCCESSFULLY")
                .build();
    }

    @Override
    public UserProfile getUserById(String userId) {
        if(userId == null || userId.isBlank()){
            throw new BadRequestException("USER ID IS REQUIRED");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("USER NOT FOUND BY GIVEN ID"));

        return UserProfile.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .activationDate(
                        user.getActivationDate()!=null ?
                                user.getActivationDate().toString()
                                :null)
                .lastUpdated(
                        user.getLastUpdated()!=null ?
                                user.getLastUpdated().toString()
                                : null)
                .build();
    }

    @Override
    public String editProfile(UserProfile profile) {

        String userId = profile.getUserId();

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new BadRequestException("INVALID USER ID"));

        // update fields
        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());
        user.setEmail(profile.getEmail());

        userRepo.save(user);

        return "PROFILE UPDATED SUCCESSFULLY";
    }

    @Override
    public String deactivateAccount(String userId) {
        return "";
    }

}
