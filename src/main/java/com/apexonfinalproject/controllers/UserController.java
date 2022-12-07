package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public User getAllUsers() {
        return User.builder()
                .id("1")
                .login("login1")
                .password("pass1")
                .email("email1")
                .fullName("name1")
                .phoneNumber("phone number1")
                .country("country1")
                .city("city1")
                .build();
    }

}
