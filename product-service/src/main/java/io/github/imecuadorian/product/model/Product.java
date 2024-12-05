package io.github.imecuadorian.product.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Product model class
 */
@Getter
@Setter
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    @NotNull(message = "{product.name.notnull}")
    @NotBlank(message = "{product.name.notblank}")
    @NotEmpty(message = "{product.name.notempty}")
    @Size(min = 3, max = 50, message = "{product.name.size}")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.-]+$", message = "{product.name.pattern}")
    private String name;

    @NotNull(message = "{product.description.notnull}")
    @NotBlank(message = "{product.description.notblank}")
    @NotEmpty(message = "{product.description.notempty}")
    @Size(min = 3, max = 255, message = "{product.description.size}")
    @Pattern(regexp = "^[a-zA-Z0-9\\s!@#$%^&*)(+=._-]+$", message = "{product.description.pattern}")
    private String description;

    @NotNull(message = "{product.price.notnull}")
    @Positive(message = "{product.price.positive}")
    @Range(min = 0, max = 999999, message = "{product.price.range}")
    @Digits(integer = 6, fraction = 2, message = "{product.price.digits}")
    private BigDecimal price;

    @NotNull(message = "{product.stock.notnull}")
    @PositiveOrZero(message = "{product.stock.positiveOrZero}")
    @Range(min = 0, max = 9999, message = "{product.stock.range}")
    private Integer stock;

    @NotNull(message = "{product.sku.notnull}")
    @NotBlank(message = "{product.sku.notblank}")
    @NotEmpty(message = "{product.sku.notempty}")
    @Size(min = 8, max = 20, message = "{product.sku.size}")
    @Pattern(regexp = "^[A-Za-z0-9_-]{8,20}$", message = "{product.sku.pattern}")
    private String sku;

    @NotNull(message = "{product.brand.notnull}")
    @NotBlank(message = "{product.brand.notblank}")
    @NotEmpty(message = "{product.brand.notempty}")
    @Size(min = 3, max = 50, message = "{product.brand.size}")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "{product.brand.pattern}")
    private String brand;

    @NotNull(message = "{product.imagesUrl.notnull}")
    @NotEmpty(message = "{product.imagesUrl.notempty}")
    @Size(min = 1, max = 5, message = "{product.imagesUrl.size}")
    private List<String> imagesUrl;

    @NotNull(message = "{product.weight.notnull}")
    @NotBlank(message = "{product.weight.notblank}")
    @NotEmpty(message = "{product.weight.notempty}")
    @Size(min = 1, max = 30, message = "{product.weight.size}")
    private String weight;

    @NotNull(message = "{product.color.notnull}")
    @NotBlank(message = "{product.color.notblank}")
    @NotEmpty(message = "{product.color.notempty}")
    private String color;

    @NotNull(message = "{product.madeIn.notnull}")
    @NotBlank(message = "{product.madeIn.notblank}")
    @NotEmpty(message = "{product.madeIn.notempty}")
    @Size(min = 3, max = 50, message = "{product.madeIn.size}")
    private String madeIn;

    @NotNull(message = "{product.manufacturer.notnull}")
    @NotBlank(message = "{product.manufacturer.notblank}")
    @NotEmpty(message = "{product.manufacturer.notempty}")
    @Size(min = 3, max = 50, message = "{product.manufacturer.size}")
    private String manufacturer;

    private ProductStatus status = ProductStatus.IN_STOCK;

    @NotNull(message = "{product.categoryId.notnull}")
    @NotBlank(message = "{product.categoryId.notblank}")
    @NotEmpty(message = "{product.categoryId.notempty}")
    @Pattern(message = "{product.categoryId.pattern}", regexp = "^[a-fA-F0-9]{24}$")
    private String categoryId;

    @CreatedDate
    private LocalDate createdAt;

    public Product(String name, String description, BigDecimal price, Integer stock, String sku, String brand, List<String> imagesUrl, String weight, String color, String madeIn, String manufacturer, String categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.sku = sku;
        this.brand = brand;
        this.imagesUrl = imagesUrl;
        this.weight = weight;
        this.color = color;
        this.madeIn = madeIn;
        this.manufacturer = manufacturer;
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
