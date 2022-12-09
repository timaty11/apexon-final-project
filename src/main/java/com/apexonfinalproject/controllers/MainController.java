package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/ad")
    public String getAdPage() {
        return "accessDenied";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

}
