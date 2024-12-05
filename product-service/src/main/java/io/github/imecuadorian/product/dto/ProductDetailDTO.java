package io.github.imecuadorian.product.dto;

import io.github.imecuadorian.product.model.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

public record ProductDetailDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String sku,
        String brand,
        List<String> imagesUrl,
        String weight,
        String color,
        String madeIn,
        String manufacturer,
        CategoryDTO category,
        ProductStatus status
) {
}
