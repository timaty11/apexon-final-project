package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.Role;
import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.RoleService;
import com.apexonfinalproject.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/register")
    public String userRegisterForm(Model model) {
        User newUser = new User();
        newUser.setActivated(true);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", newUser);
        model.addAttribute("roles", roles);
        return "userForm";
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String userRegister(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setActivated(true);

        log.info(user.toString());
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

    @GetMapping("/authorized")
    public String userAuthorizedPage(Model model) {
        return "authorized";
    }

}
