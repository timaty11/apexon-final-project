package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.Role;
import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.RoleService;
import com.apexonfinalproject.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/")
    public String getControlPanelPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("listUsers", users);
        return "userControlPanel";
    }

    @GetMapping("/new")
    public String getUserCreatePage(Authentication authentication, Model model) {
        User user = new User();
        model.addAttribute("user", user);

        // If the authorized user make able to change roles
        if (authentication.getAuthorities().toString().contains("ADMIN")) {
            log.info("This is admin");
            List<Role> roles = roleService.getAllRoles();
            model.addAttribute("roles", roles);
        }
        return "userForm";
    }

    @PostMapping("/")
    public String createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        log.info(user.toString());
        userService.addUser(user);
        return "redirect:/users/";
    }

    @GetMapping("/edit/{id}")
    public String getUserEditPage(@PathVariable String id, Authentication authentication, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        // If the authorized user make able to change roles
        if (authentication.getAuthorities().toString().contains("ADMIN")) {
            log.info("This is admin");
            List<Role> roles = roleService.getAllRoles();
            model.addAttribute("roles", roles);
        }
        return "userForm";
    }

    @PostMapping(value = "/update/{id}", consumes = "application/x-www-form-urlencoded")
    public String updateUser(@PathVariable String id, User user) {
        userService.updateUser(id, user);
        return "redirect:/home";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "redirect:/users/";
    }

}
