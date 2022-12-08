package com.apexonfinalproject.services;

import com.apexonfinalproject.model.User;
import com.apexonfinalproject.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public void addUser(User user) {
        log.info(user.toString());
        String id = UUID.randomUUID().toString();
        log.info("Create user with id: '{}'", id);
        user.setId(id);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        log.info("Get user data with id: '{}'", id);
        return userRepository.findById(id).orElseThrow(() -> {
            log.error("User with id: '{}' not found!", id);
            throw new RuntimeException("User not found");
        });
    }

}
