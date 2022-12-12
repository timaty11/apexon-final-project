package com.apexonfinalproject.services;

import com.apexonfinalproject.exceptions.UserNotFoundException;
import com.apexonfinalproject.model.order.CartInfo;
import com.apexonfinalproject.model.order.Order;
import com.apexonfinalproject.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class OrderService {

    private static String  ERROR_ORDER_NOT_FOUND_TEMPLATE = "Order with id: %s not found!";

    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(CartInfo cartInfo) {
        log.info(cartInfo.toString());
        String id = UUID.randomUUID().toString();
        log.info("Create order with id: '{}'", id);

        Order order = new Order();
        order.setId(id);
        order.setAmount(cartInfo.getAmountTotal());
        order.setCustomerAddress(cartInfo.getCustomerInfo().getAddress());
        order.setCustomerEmail(cartInfo.getCustomerInfo().getEmail());
        order.setCustomerName(cartInfo.getCustomerInfo().getName());
        order.setCustomerPhone(cartInfo.getCustomerInfo().getPhone());
        order.setCreateDate(new Date());
        order.setOrderNum(1);

        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        log.info("Get all orders");
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        log.info("Get order data with id: '{}'", id);
        return orderRepository.findById(id).orElseThrow(() -> {
            log.error(String.format(ERROR_ORDER_NOT_FOUND_TEMPLATE, id));
            return new UserNotFoundException(String.format(ERROR_ORDER_NOT_FOUND_TEMPLATE, id));
        });
    }

    public void updateOrder(String id, Order newOrderData) {
        log.info("Update order data with id: '{}'", id);
        if (!orderRepository.existsById(id)) {
            log.error(String.format(ERROR_ORDER_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_ORDER_NOT_FOUND_TEMPLATE, id));
        }

        Order prevOrderData = getOrderById(id);
        orderRepository.save(Order.builder()
                .id(id)
                .amount(newOrderData.getAmount() == 0 ? prevOrderData.getAmount() : newOrderData.getAmount())
                .customerAddress(newOrderData.getCustomerAddress() == null ? prevOrderData.getCustomerAddress() : newOrderData.getCustomerAddress())
                .customerEmail(newOrderData.getCustomerEmail() == null ? prevOrderData.getCustomerEmail() : newOrderData.getCustomerEmail())
                .customerName(newOrderData.getCustomerName() == null ? prevOrderData.getCustomerName() : newOrderData.getCustomerName())
                .customerPhone(newOrderData.getCustomerPhone() == null ? prevOrderData.getCustomerPhone() : newOrderData.getCustomerPhone())
                .orderNum(newOrderData.getOrderNum() == 0 ? prevOrderData.getOrderNum() : newOrderData.getOrderNum())
                .build()
        );
    }

    public void deleteOrder(String id) {
        log.info("Delete order data with id: '{}'", id);
        if (!orderRepository.existsById(id)) {
            log.error(String.format(ERROR_ORDER_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_ORDER_NOT_FOUND_TEMPLATE, id));
        }
        orderRepository.deleteById(id);
    }

}
