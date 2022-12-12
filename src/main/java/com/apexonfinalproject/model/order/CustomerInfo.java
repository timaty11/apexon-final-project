package com.apexonfinalproject.model.order;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class CustomerInfo {

    private String name;
    private String address;
    private String email;
    private String phone;

    public CustomerInfo() {}

}
