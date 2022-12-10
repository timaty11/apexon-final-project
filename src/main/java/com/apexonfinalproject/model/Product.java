package com.apexonfinalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @JsonIgnore
    private String id;

    @Column(length = 255, name = "product_name", unique = true, nullable = false)
    private String productName;

    @Column(length = 255, name = "product_description")
    private String productDescription;

    @Column(name = "product_price", nullable = false)
    @Builder.Default
    private Double productPrice = 0.01;

    @Column(name = "product_amount", nullable = false)
    @Builder.Default
    private Integer productAmount = 0;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories;

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

}
