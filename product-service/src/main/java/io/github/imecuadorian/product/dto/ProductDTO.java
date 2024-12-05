package io.github.imecuadorian.product.dto;

import io.github.imecuadorian.product.model.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        String categoryId,
        List<String> imagesUrl,
        ProductStatus status
) {
}
