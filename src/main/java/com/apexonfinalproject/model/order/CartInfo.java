package com.apexonfinalproject.model.order;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@Slf4j
public class CartInfo {

    private int orderNum;
    private CustomerInfo customerInfo;
    private final List<CartLineInfo> cartLineInfoList = new ArrayList<CartLineInfo>();

    public CartInfo() {}

    private CartLineInfo findLineByCode(String code) {
        for (CartLineInfo lineInfo : this.cartLineInfoList) {
            if (lineInfo.getProductInfo().getId().equals(code)) {
                return lineInfo;
            }
        }
        return null;
    }

    public void addProduct(ProductInfo productInfo, int quantity) {
        CartLineInfo lineInfo = this.findLineByCode(productInfo.getId());

        if (lineInfo == null) {
            log.info("nema takogo govna");
            lineInfo = new CartLineInfo();
            lineInfo.setProductInfo(productInfo);
            lineInfo.setQuantity(0);
            this.cartLineInfoList.add(lineInfo);
        }

        int newQuantity = lineInfo.getQuantity() + quantity;
        if (newQuantity <= 0) {
            this.cartLineInfoList.remove(lineInfo);
        } else {
            lineInfo.setQuantity(newQuantity);
        }
    }

    public void updateProduct(String id, int quantity) {
        CartLineInfo lineInfo = this.findLineByCode(id);

        if (lineInfo != null) {
            if (quantity <= 0) {
                this.cartLineInfoList.remove(lineInfo);
            } else {
                lineInfo.setQuantity(quantity);
            }
        }
    }

    public void removeProduct(ProductInfo productInfo) {
        CartLineInfo lineInfo = this.findLineByCode(productInfo.getId());

        if (lineInfo != null) {
            this.cartLineInfoList.remove(lineInfo);
        }
    }

    public boolean isEmpty() {
        return this.cartLineInfoList.isEmpty();
    }

    public int getQuantityTotal() {
        int quantity = 0;
        for (CartLineInfo lineInfo : this.cartLineInfoList) {
            quantity += lineInfo.getQuantity();
        }
        return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLineInfo lineInfo : this.cartLineInfoList) {
            total += lineInfo.getProductInfo().getPrice() * lineInfo.getQuantity();
        }
        return total;
    }

    public void updateQuantity(CartInfo cartInfo) {
        if (cartInfo != null) {
            List<CartLineInfo> lineInfos = cartInfo.getCartLineInfoList();
            for (CartLineInfo lineInfo : lineInfos) {
                this.updateProduct(lineInfo.getProductInfo().getId(), lineInfo.getQuantity());
            }
        }
    }

}
