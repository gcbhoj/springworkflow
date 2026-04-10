package com.bhoj.springbootapp.services;

import com.bhoj.springbootapp.DTO.RegistrationRequest;
import com.bhoj.springbootapp.DTO.RegistrationResponse;
import com.bhoj.springbootapp.DTO.UserProfile;
import com.bhoj.springbootapp.beans.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    RegistrationResponse addNewUser(RegistrationRequest request);
    UserProfile getUserById(String userId);
    String editProfile(UserProfile profile);
    String deactivateAccount(String userId);
    String activateAccount(String userId);

    List<UserProfile> getAll();

}
