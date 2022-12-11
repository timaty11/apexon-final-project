package com.apexonfinalproject.repositories;

import com.apexonfinalproject.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {}
