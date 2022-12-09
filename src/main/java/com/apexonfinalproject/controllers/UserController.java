package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.Role;
import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.RoleService;
import com.apexonfinalproject.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String getControlPanelPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("listUsers", users);
        return "controlPanel";
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

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User user) {
        userService.updateUser(id, user);
        return "redirect:/home";
    }

}
