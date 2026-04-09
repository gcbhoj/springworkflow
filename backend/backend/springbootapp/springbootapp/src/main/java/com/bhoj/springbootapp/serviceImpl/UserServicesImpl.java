package com.bhoj.springbootapp.serviceImpl;

import com.bhoj.springbootapp.DTO.RegistrationRequest;
import com.bhoj.springbootapp.DTO.RegistrationResponse;
import com.bhoj.springbootapp.DTO.UserProfile;
import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.enums.UserStatus;
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
            throw new UserCreationException("USER ID IS REQUIRED");
        }

        User user = verifyUserStatus(userId);

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

        User user = verifyUserStatus(userId);

        // update fields
        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());
        user.setEmail(profile.getEmail());

        userRepo.save(user);

        return "PROFILE UPDATED SUCCESSFULLY";
    }

    @Override
    public String deactivateAccount(String userId) {

        if (userId == null || userId.isBlank()) {
            throw new UserCreationException("USER ID IS REQUIRED");
        }

        User user = verifyUserStatus(userId);

        user.setStatus(UserStatus.DEACTIVATED);

        userRepo.save(user);

        return "ACCOUNT HAS BEEN DEACTIVATED SUCCESSFULLY";
    }

    @Override
    public String activateAccount(String userId) {

        if (userId == null || userId.isBlank()) {
            throw new UserCreationException("USER ID IS REQUIRED");
        }

        User user = userRepo.findById(userId)
                .orElseThrow(()-> new UserCreationException("USER NOT FOUND BY GIVEN ID"));

        if(user.getStatus() == UserStatus.DEACTIVATED){
            user.setStatus(UserStatus.ACTIVE);

            userRepo.save(user);

            return "USER STATUS SET TO ACTIVE";
        }
        return "ACCOUNT HAS BEEN ACTIVATED";
    }

    public User verifyUserStatus(String userId){

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserCreationException("NO USER FOUND BY GIVEN ID"));

        if (user.getStatus().equals(UserStatus.DEACTIVATED)|| user.getStatus().equals(UserStatus.ON_HOLD) ) {
            throw new UserCreationException("ACCOUNT IS DEACTIVATED OR IS ON HOLD");
        }

        return  user;

    }

}
