package com.apexonfinalproject.controllers;

import com.apexonfinalproject.model.Product;
import com.apexonfinalproject.model.order.CartInfo;
import com.apexonfinalproject.model.order.CustomerInfo;
import com.apexonfinalproject.model.order.Order;
import com.apexonfinalproject.model.order.ProductInfo;
import com.apexonfinalproject.services.OrderService;
import com.apexonfinalproject.services.ProductService;
import com.apexonfinalproject.utils.CartUtils;
import com.apexonfinalproject.utils.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    KafkaProducer kafkaProducer;

    @GetMapping("/add-product/{productId}")
    public String addProductHandle(HttpServletRequest request, Model model, @PathVariable String productId) {
        Product product = null;
        if (productId != null && productId.length() > 0) {
            product = productService.getProductById(productId);
        }
        if (product != null) {
            CartInfo cartInfo = CartUtils.getCartInSession(request);
            ProductInfo productInfo = new ProductInfo(product);
            log.info(productInfo.toString());
            cartInfo.addProduct(productInfo, 1);
        }
        return "redirect:/home";
    }

    @GetMapping("/remove-product/{productId}")
    public String removeProductHandle(HttpServletRequest request, Model model, @PathVariable String productId) {
        Product product = null;
        if (productId != null && productId.length() > 0) {
            product = productService.getProductById(productId);
        }
        if (product != null) {
            CartInfo cartInfo = CartUtils.getCartInSession(request);
            ProductInfo productInfo = new ProductInfo(product);
            cartInfo.removeProduct(productInfo);
        }
        return "redirect:/mainPage";
    }

    @GetMapping("/shopping-cart")
    public String getShoppingCartPage(HttpServletRequest request, Model model) {
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        model.addAttribute("cartForm", cartInfo);
        return "shoppingCart";
    }

    @PostMapping("/shopping-cart")
    public String shoppingCartUpdateProductQuantity(HttpServletRequest request, Model model) {
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        cartInfo.updateQuantity((CartInfo) model.getAttribute("cartForm"));
        return "redirect:/orders/shopping-cart";
    }

    @GetMapping("/confirm")
    public String getOrderConfirmPage(HttpServletRequest request, Model model) {
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        if (cartInfo == null || cartInfo.isEmpty()) {
            return "redirect:/orders/shopping-cart";
        } else if (cartInfo.getCustomerInfo() == null) {
            CustomerInfo customerInfo = new CustomerInfo();
            cartInfo.setCustomerInfo(new CustomerInfo());
            model.addAttribute("customerInfo", customerInfo);
        } else {
            model.addAttribute("customerInfo", cartInfo.getCustomerInfo());
        }
        model.addAttribute("cartInfo", cartInfo);

        return "orderConfirm";
    }

    @PostMapping("/confirm")
    public String confirmOrderHandle(HttpServletRequest request, CustomerInfo customerInfo) {
        CartInfo cartInfo = CartUtils.getCartInSession(request);
        cartInfo.setCustomerInfo(customerInfo);
        log.info("Cart info: " + cartInfo);
        if (cartInfo == null || cartInfo.isEmpty()) {
            return "redirect:/home";
        } else {
            Order order = orderService.addOrder(cartInfo);
            kafkaProducer.sendMessage("Created order with id: " + order.getId() + " Order data: " + order);
        }

        return "redirect:/home";
    }

}
