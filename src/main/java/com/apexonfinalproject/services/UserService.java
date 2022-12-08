package com.apexonfinalproject.services;

import com.apexonfinalproject.exceptions.UserNotFoundException;
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

    private static String ERROR_USER_NOT_FOUND_TEMPLATE = "User with id: %s not found!";

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
            log.error(String.format(ERROR_USER_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_USER_NOT_FOUND_TEMPLATE, id));
        });
    }

    public void updateUser(String id, User newUserData) {
        log.info("Update user data with id: '{}'", id);
        if (!userRepository.existsById(id)) {
            log.error(String.format(ERROR_USER_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_USER_NOT_FOUND_TEMPLATE, id));
        }

        User prevUserData = getUserById(id);
        userRepository.save(User.builder()
                .id(id)
                .login(newUserData.getLogin() == null ? prevUserData.getLogin() : newUserData.getLogin())
                .password(newUserData.getPassword() == null ? prevUserData.getPassword() : newUserData.getPassword())
                .email(newUserData.getEmail() == null ? prevUserData.getEmail() : newUserData.getEmail())
                .fullName(newUserData.getFullName() == null ? prevUserData.getFullName() : newUserData.getFullName())
                .phoneNumber(newUserData.getPhoneNumber() == null ? prevUserData.getPhoneNumber() : newUserData.getPhoneNumber())
                .country(newUserData.getCountry() == null ? prevUserData.getCountry() : newUserData.getCountry())
                .city(newUserData.getCity() == null ? prevUserData.getCity() : newUserData.getCity())
                .build()
        );
    }

    public void deleteUser(String id) {
        log.info("Update user data with id: '{}'", id);
        if (!userRepository.existsById(id)) {
            log.error(String.format(ERROR_USER_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_USER_NOT_FOUND_TEMPLATE, id));
        }
        userRepository.deleteById(id);
    }

}
