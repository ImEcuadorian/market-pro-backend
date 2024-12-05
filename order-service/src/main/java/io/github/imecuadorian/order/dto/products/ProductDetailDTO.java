package io.github.imecuadorian.order.dto.products;

import java.math.BigDecimal;
import java.util.List;

public record ProductDetailDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
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
