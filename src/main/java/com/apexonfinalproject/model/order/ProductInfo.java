package com.apexonfinalproject.model.order;

import com.apexonfinalproject.model.Product;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ProductInfo {

    private String id;
    private String productName;
    private double price;

    public ProductInfo(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.price = product.getProductPrice();
    }

}
