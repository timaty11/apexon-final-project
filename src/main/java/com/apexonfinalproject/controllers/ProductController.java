package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.Category;
import com.apexonfinalproject.model.Product;
import com.apexonfinalproject.services.CategoryService;
import com.apexonfinalproject.services.ProductService;
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
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/")
    public String getControlPanelPage(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("listProducts", products);
        return "productControlPanel";
    }

    @GetMapping("/new")
    public String getProductCreatePage(Model model) {
        Product product = new Product();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("product", product);
        model.addAttribute("listCategories", categories);
        return "productForm";
    }

    @PostMapping("/create")
    public String createProduct(Product product) {
        log.info(product.toString());
        productService.addProduct(product);
        return "redirect:/products/";
    }

    @GetMapping("/edit/{id}")
    public String getProductEditPage(@PathVariable String id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productForm";
    }

    @PostMapping(value = "/update/{id}", consumes = "application/x-www-form-urlencoded")
    public String updateProduct(@PathVariable String id, Product product) {
        productService.updateProduct(id, product);
        return "redirect:/products/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return "redirect:/products/";
    }

}
