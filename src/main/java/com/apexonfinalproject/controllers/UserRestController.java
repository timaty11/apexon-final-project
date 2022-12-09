package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserRestController {

    private UserService userService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserData(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody User user) {
        userService.addUser(user);
        return "created";
    }

    @PutMapping("/active/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String activateUser(@PathVariable String id) {
        userService.activateUser(id);
        return "activated";
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updateUser(@PathVariable String id, @RequestBody User user) {
        userService.updateUser(id, user);
        return "activated";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

}
