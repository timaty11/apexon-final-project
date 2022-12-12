package com.apexonfinalproject;

import com.apexonfinalproject.model.Category;
import com.apexonfinalproject.model.Product;
import com.apexonfinalproject.model.Role;
import com.apexonfinalproject.repositories.CategoryRepository;
import com.apexonfinalproject.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest(classes = ApexonFinalProjectApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ProductsRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void testCreateFirstCategory() {
        Category category1 = new Category("1", "Computes", "Everything about computers", new HashSet<>());
        Category savedCategory = categoryRepository.save(category1);
        Assertions.assertThat(savedCategory.getId()).isNotEmpty();
    }

    @Test
    public void testCreateMultipleCategories() {
        Category category1 = new Category("1", "Computes", "Everything about computers", new HashSet<>());
        Category category2 = new Category("2", "Cars", "Everything about Cars", new HashSet<>());
        Category category3 = new Category("3", "Fruits", "Everything about Fruits", new HashSet<>());

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        Set<Category> categorySet1 = new HashSet<Category>();
        categorySet1.add(category1);
        Set<Category> categorySet2 = new HashSet<Category>();
        categorySet1.add(category2);
        Set<Category> categorySet3 = new HashSet<Category>();
        categorySet1.add(category3);

        Product product1 = new Product(
                "1",
                "laptopHP",
                "powerful laptop",
                999.99,
                2,
                categorySet1
        );
        Product product2 = new Product(
                "2",
                "Audi SHPROT",
                "powerful car",
                150000.00,
                5,
                categorySet2
        );
        Product product3 = new Product(
                "3",
                "Apple",
                "pen pineaple apple pen",
                1.59,
                25,
                categorySet3
        );

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }

}
