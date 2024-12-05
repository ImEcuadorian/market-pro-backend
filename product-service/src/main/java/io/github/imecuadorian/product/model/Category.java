package io.github.imecuadorian.product.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Getter
@Setter
@Document(collection = "categories")
public class Category {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    @NotNull(message = "{category.name.notnull}")
    @NotBlank(message = "{category.name.notblank}")
    @NotEmpty(message = "{category.name.notempty}")
    @Size(min = 3, max = 50, message = "{category.name.size}")
    @Pattern(regexp = "^[a-zA-Z0-9\\s.-]+$", message = "{category.name.pattern}")
    private String name;

    @NotNull(message = "{category.description.notnull}")
    @NotBlank(message = "{category.description.notblank}")
    @NotEmpty(message = "{category.description.notempty}")
    @Size(min = 3, max = 255, message = "{category.description.size}")
    @Pattern(regexp = "^[a-zA-Z0-9\\s!@#$%^&*)(+=._-]+$", message = "{category.description.pattern}")
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
