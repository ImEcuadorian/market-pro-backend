package io.github.imecuadorian.product.repositories;

import io.github.imecuadorian.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    void deleteAllByCategoryId(String categoryId);

    @Query("{'id': ?0}")
    @Update(value = "{'$inc': {'stockQuantity': ?1}}")
    void updateProductStock(String id, Integer stockQuantity);

    @Query("{'id': ?0}")
    @Update(value = "{'$set': {'status': ?1}}")
    void updateProductStatus(String id, String status);
}
