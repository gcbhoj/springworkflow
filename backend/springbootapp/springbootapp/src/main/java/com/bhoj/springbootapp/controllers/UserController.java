package com.bhoj.springbootapp.controllers;


import com.bhoj.springbootapp.DTO.RegistrationRequest;
import com.bhoj.springbootapp.DTO.RegistrationResponse;
import com.bhoj.springbootapp.DTO.UserProfile;
import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.serviceImpl.UserServicesImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserServicesImpl userService;

    @PostMapping("/user-register")
    public ResponseEntity<RegistrationResponse> addNewUser(@Valid @RequestBody RegistrationRequest request) {

        RegistrationResponse response = userService.addNewUser(request);

        return ResponseEntity.ok(response);

    }
    @GetMapping("/getUserById")
    public ResponseEntity<UserProfile> getUserById(@RequestParam String userId){

        UserProfile profile = userService.getUserById(userId);

        return ResponseEntity.ok(profile);

    }

    @PatchMapping("/editProfile")
    public ResponseEntity<String> editProfile(@Valid @RequestBody UserProfile profile){

        String result = userService.editProfile(profile);

        return  ResponseEntity.ok(result);

    }

    @PatchMapping("/user-deactivate")
    public ResponseEntity<String> deactivate(@RequestParam String userId){

        String result = userService.deactivateAccount(userId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/user-reactivate")
    public ResponseEntity <String> reactivate(@RequestParam String userId){

        String result = userService.activateAccount(userId);

        return  ResponseEntity.ok(result);
    }

}
