package io.github.imecuadorian.product.services;

import io.github.imecuadorian.product.dto.ProductDTO;
import io.github.imecuadorian.product.dto.ProductDetailDTO;
import io.github.imecuadorian.product.model.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProducts();

    Product createProduct(Product product);

    ProductDetailDTO getProductById(String id);
    void updateProductStock(String id, Integer quantity);
    void updateProductStatus(String id, String status);
    void deleteProduct(String id);

}
