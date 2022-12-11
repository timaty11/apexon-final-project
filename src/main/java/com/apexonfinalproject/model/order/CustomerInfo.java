package com.apexonfinalproject.model.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerInfo {

    private String name;
    private String address;
    private String email;
    private String phone;

}
