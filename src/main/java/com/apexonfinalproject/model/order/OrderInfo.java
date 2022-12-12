package com.apexonfinalproject.model.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderInfo {

    private String id;
    private Date orderDate;
    private int orderNum;
    private double amount;
    private String customerName;
    private String customerAddress;
    private String customerEmail;
    private String customerPhone;
    private List<OrderDetailInfo> orderDetails;

}
