package com.apexonfinalproject.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = "order_num")})
public class Order {

    @Id
    @JsonIgnore
    private String id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "customer_address", length = 255, nullable = false)
    private String customerAddress;

    @Column(name = "customer_email", length = 255, nullable = false)
    private String customerEmail;

    @Column(name = "customer_name", length = 255, nullable = false)
    private String customerName;

    @Column(name = "customer_phone", length = 255, nullable = false)
    private String customerPhone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "order_num", nullable = false)
    private int orderNum;

}
