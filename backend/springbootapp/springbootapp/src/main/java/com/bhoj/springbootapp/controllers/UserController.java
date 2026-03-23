package com.bhoj.springbootapp.controllers;


import com.bhoj.springbootapp.DTO.RegistrationRequest;
import com.bhoj.springbootapp.beans.User;
import com.bhoj.springbootapp.services.UserServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserServices userService;

    @PostMapping("/user-register")
    public ResponseEntity<String> addNewUser(@Valid @RequestBody RegistrationRequest request) {

        userService.saveUser(request);

        return ResponseEntity.ok("User added successfully");
    }
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestParam String userId){

        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

}
