package io.github.imecuadorian.product.mapper;

import io.github.imecuadorian.product.dto.ProductDTO;
import io.github.imecuadorian.product.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(@NotNull Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategoryId(),
                product.getImagesUrl(),
                product.getStatus()
        );
    }
}
