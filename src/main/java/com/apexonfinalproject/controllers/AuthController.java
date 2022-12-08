package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;

    @GetMapping("/register")
    public String userRegisterForm(Model model) {
        User newUser = new User();
        newUser.setActivated(true);

        model.addAttribute("user", newUser);
        return "registration";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String userRegister(User user) {
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String userLoginForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String userLogin(User user) {
        return "redirect:/home";
    }

}