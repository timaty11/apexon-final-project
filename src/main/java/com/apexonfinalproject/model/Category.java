package com.apexonfinalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "categories")
public class Category {

    @Id
    @JsonIgnore
    private String id;

    @Column(length = 255, name = "category_name", nullable = false, unique = true)
    private String categoryName;

    @Column(length = 255, name = "category_description")
    private String categoryDescription;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;

    @Override
    public String toString() {
        return categoryName + ": " + categoryDescription;
    }

}
