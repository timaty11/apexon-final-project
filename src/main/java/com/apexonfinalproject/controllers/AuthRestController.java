package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {

    private UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void userRegister(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public void userLogin(@RequestBody User user) {

    }

}
