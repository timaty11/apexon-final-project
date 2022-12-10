package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.Category;
import com.apexonfinalproject.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categories")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/")
    public String getCategoryPanelPage(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("listCategories", categories);
        return "categoryControlPanel";
    }

    @GetMapping("/new")
    public String getCategoryCreatePage(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "categoryForm";
    }

    @PostMapping("/create")
    public String createCategory(Category category) {
        log.info(category.toString());
        categoryService.addCategory(category);
        return "redirect:/categories/";
    }

    @GetMapping("/edit/{id}")
    public String getCategoryEditPage(@PathVariable String id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "categoryForm";
    }

    @PostMapping(value = "/update/{id}", consumes = "application/x-www-form-urlencoded")
    public String updateCategory(@PathVariable String id, Category category) {
        categoryService.updateCategory(id, category);
        return "redirect:/categories/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories/";
    }

}
