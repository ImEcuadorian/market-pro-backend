package io.github.imecuadorian.product.services.impl;

import io.github.imecuadorian.product.dto.CategoryDTO;
import io.github.imecuadorian.product.exception.CategoryNotFoundException;
import io.github.imecuadorian.product.mapper.CategoryDTOMapper;
import io.github.imecuadorian.product.model.Category;
import io.github.imecuadorian.product.repositories.CategoryRepository;
import io.github.imecuadorian.product.repositories.ProductRepository;
import io.github.imecuadorian.product.services.CategoryService;
import io.github.imecuadorian.product.utils.MessagesUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryDTOMapper categoryDTOMapper;

    private final MessagesUtils messageUtils;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository, CategoryDTOMapper categoryDTOMapper, MessagesUtils messageUtils) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.categoryDTOMapper = categoryDTOMapper;
        this.messageUtils = messageUtils;
    }


    @Override
    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryDTOMapper::mapEntityToDTO).toList();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        return categoryRepository.findById(id)
                .map(categoryDTOMapper::mapEntityToDTO)
                .orElseThrow(() -> new CategoryNotFoundException(messageUtils, id));
    }

    @Override
    public void updateCategoryById(String id, Category category) {
        categoryRepository.findById(id).stream()
                .findFirst()
                .ifPresentOrElse(category1 -> {
                    category.setId(category1.getId());
                    category.setName(category.getName());
                    category.setDescription(category.getDescription());
                    categoryRepository.save(category);
                }, () -> {
                    throw new CategoryNotFoundException(messageUtils, id);
                });
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.findById(id).stream()
                .findFirst()
                .ifPresentOrElse(category -> {
                    productRepository.deleteAllByCategoryId(id);
                    categoryRepository.deleteById(id);
                }, () -> {
                    throw new CategoryNotFoundException(messageUtils, id);
                });

    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
