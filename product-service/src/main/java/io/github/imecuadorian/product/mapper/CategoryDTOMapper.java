package io.github.imecuadorian.product.mapper;

import io.github.imecuadorian.product.dto.CategoryDTO;
import io.github.imecuadorian.product.model.Category;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOMapper implements Mapper<Category, CategoryDTO> {

    @Override
    public CategoryDTO mapEntityToDTO(@NotNull Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

    @Override
    public Category mapDTOToEntity(@NotNull CategoryDTO categoryDTO) {
        return new Category(
                categoryDTO.name(),
                categoryDTO.description()
        );
    }
}
