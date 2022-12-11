package com.apexonfinalproject.model.order;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class CartLineInfo {

    private ProductInfo productInfo;

    @Builder.Default
    private int quantity = 0;

    public CartLineInfo() {}

}
