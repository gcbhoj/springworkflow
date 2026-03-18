package com.bhoj.springbootapp.controllers;


import com.bhoj.springbootapp.DTO.RegistrationRequest;
import com.bhoj.springbootapp.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody RegistrationRequest request) {

        userService.saveUser(request);

        return ResponseEntity.ok("User added successfully");
    }
}
