package com.apexonfinalproject.model.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDetailInfo {

    private String id;
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private double amount;

}
