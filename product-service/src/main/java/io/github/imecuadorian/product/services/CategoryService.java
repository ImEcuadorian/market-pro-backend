package io.github.imecuadorian.product.services;

import io.github.imecuadorian.product.dto.CategoryDTO;
import io.github.imecuadorian.product.model.Category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategories();
    Category createCategory(Category category);
    CategoryDTO getCategoryById(String id);
    void updateCategoryById(String id, Category category);
    void deleteCategory(String id);
    boolean existsByName(String name);

}
