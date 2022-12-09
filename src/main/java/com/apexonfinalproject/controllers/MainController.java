package com.apexonfinalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
