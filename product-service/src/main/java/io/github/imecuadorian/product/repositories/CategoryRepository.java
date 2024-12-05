package io.github.imecuadorian.product.repositories;

import io.github.imecuadorian.product.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
    boolean existsByName(String name);

}
