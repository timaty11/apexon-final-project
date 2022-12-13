package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/register")
    public String userRegisterForm(Model model) {
        User newUser = new User();
        newUser.setActivated(true);
        model.addAttribute("user", newUser);
        return "userForm";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String userRegister(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActivated(true);
        log.info(user.toString());
        userService.addUser(user);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String userLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/authorized")
    public String userAuthorizedPage() {
        return "authorized";
    }

}
