package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.Category;
import com.apexonfinalproject.model.Product;
import com.apexonfinalproject.model.User;
import com.apexonfinalproject.services.CategoryService;
import com.apexonfinalproject.services.ProductService;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/home")
    public String getHomePage(Model model) {
        List<Product> products = productService.getAllProducts();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("listProducts", products);
        model.addAttribute("listCategories", categories);
        return "mainPage";
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
