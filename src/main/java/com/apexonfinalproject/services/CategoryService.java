package com.apexonfinalproject.services;

import com.apexonfinalproject.exceptions.UserNotFoundException;
import com.apexonfinalproject.model.Category;
import com.apexonfinalproject.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryService {

    private static String  ERROR_CATEGORY_NOT_FOUND_TEMPLATE = "Category with id: %s not found!";

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(Category category) {
        log.info(category.toString());
        String id = UUID.randomUUID().toString();
        log.info("Create category with id: '{}'", id);
        category.setId(id);
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        log.info("Get all categories");
        return categoryRepository.findAll();
    }

    public Category getCategoryById(String id) {
        log.info("Get category data with id: '{}'", id);
        return categoryRepository.findById(id).orElseThrow(() -> {
            log.error(String.format(ERROR_CATEGORY_NOT_FOUND_TEMPLATE, id));
            return new UserNotFoundException(String.format(ERROR_CATEGORY_NOT_FOUND_TEMPLATE, id));
        });
    }

    public void updateCategory(String id, Category newCategoryData) {
        log.info("Update category data with id: '{}'", id);
        if (!categoryRepository.existsById(id)) {
            log.error(String.format(ERROR_CATEGORY_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_CATEGORY_NOT_FOUND_TEMPLATE, id));
        }

        Category prevCategoryData = getCategoryById(id);
        categoryRepository.save(Category.builder()
                .id(id)
                .categoryName(newCategoryData.getCategoryName() == null ? prevCategoryData.getCategoryName() : newCategoryData.getCategoryName())
                .categoryDescription(newCategoryData.getCategoryDescription() == null ? prevCategoryData.getCategoryDescription() : newCategoryData.getCategoryDescription())
                .build()
        );
    }

    public void deleteCategory(String id) {
        log.info("Delete category data with id: '{}'", id);
        if (!categoryRepository.existsById(id)) {
            log.error(String.format(ERROR_CATEGORY_NOT_FOUND_TEMPLATE, id));
            throw new UserNotFoundException(String.format(ERROR_CATEGORY_NOT_FOUND_TEMPLATE, id));
        }
        categoryRepository.deleteById(id);
    }

}
