package com.apexonfinalproject.services;

import com.apexonfinalproject.exceptions.UserNotFoundException;
import com.apexonfinalproject.model.Category;
import com.apexonfinalproject.model.Product;
import com.apexonfinalproject.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private static String  ERROR_PRODUCT_NOT_FOUND_TEMPLATE = "Product with id: %s not found!";

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product) {
        log.info(product.toString());
        String id = UUID.randomUUID().toString();
        log.info("Create product with id: '{}'", id);
        product.setId(id);
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        log.info("Get all products");
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        log.info("Get product data with id: '{}'", id);
        return productRepository.findById(id).orElseThrow(() -> {
            log.error(String.format(ERROR_PRODUCT_NOT_FOUND_TEMPLATE, id));
            return new UserNotFoundException(String.format(ERROR_PRODUCT_NOT_FOUND_TEMPLATE, id));
        });
    }

    public void updateProduct(String id, Product newProductData) {
        log.info("Update product data with id: '{}'", id);
        if (!productRepository.existsById(id)) {
            log.error(String.format(ERROR_PRODUCT_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_PRODUCT_NOT_FOUND_TEMPLATE, id));
        }

        Product prevProductData = getProductById(id);
        productRepository.save(Product.builder()
                .id(id)
                .productName(newProductData.getProductName() == null ? prevProductData.getProductName() : newProductData.getProductName())
                .productDescription(newProductData.getProductDescription() == null ? prevProductData.getProductDescription() : newProductData.getProductDescription())
                .productPrice(newProductData.getProductPrice() == null ? prevProductData.getProductPrice() : newProductData.getProductPrice())
                .productAmount(newProductData.getProductAmount() == null ? prevProductData.getProductAmount() : newProductData.getProductAmount())
                .categories(newProductData.getCategories() == null ? prevProductData.getCategories() : newProductData.getCategories())
                .build()
        );
    }

    public void deleteProduct(String id) {
        log.info("Delete product data with id: '{}'", id);
        if (!productRepository.existsById(id)) {
            log.error(String.format(ERROR_PRODUCT_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_PRODUCT_NOT_FOUND_TEMPLATE, id));
        }
        productRepository.deleteById(id);
    }

}
