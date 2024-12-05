package io.github.imecuadorian.product.services.impl;

import io.github.imecuadorian.product.dto.ProductDTO;
import io.github.imecuadorian.product.dto.ProductDetailDTO;
import io.github.imecuadorian.product.exception.ProductNotFoundException;
import io.github.imecuadorian.product.mapper.ProductDTOMapper;
import io.github.imecuadorian.product.mapper.ProductDetailDTOMapper;
import io.github.imecuadorian.product.model.Product;
import io.github.imecuadorian.product.repositories.ProductRepository;
import io.github.imecuadorian.product.services.ProductService;
import io.github.imecuadorian.product.utils.MessagesUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDTOMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductDetailDTOMapper productDetailDTOMapper;
    private final MessagesUtils messagesUtils;

    public ProductServiceImpl(ProductRepository productRepository, ProductDetailDTOMapper productDetailDTOMapper, ProductDTOMapper productMapper, MessagesUtils messagesUtils) {
        this.productRepository = productRepository;
        this.productDetailDTOMapper = productDetailDTOMapper;
        this.productMapper = productMapper;
        this.messagesUtils = messagesUtils;
    }


    @Override
    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream()
                .map(productMapper)
                .toList();
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public ProductDetailDTO getProductById(String id) {
        return productRepository.findById(id)
                .map(productDetailDTOMapper::mapEntityToDTO)
                .orElseThrow(() -> new ProductNotFoundException(messagesUtils, id));
    }

    @Override
    public void updateProductStock(String id, Integer quantity) {
        productRepository.findById(id).stream()
                .findFirst()
                .ifPresentOrElse(product -> productRepository.updateProductStock(id, quantity),
                        () -> {
                            throw new ProductNotFoundException(messagesUtils, id);
                        });
    }

    @Override
    public void updateProductStatus(String id, String status) {
        productRepository.findById(id).stream()
                .findFirst()
                .ifPresentOrElse(product -> productRepository.updateProductStatus(id, status),
                        () -> {
                            throw new ProductNotFoundException(messagesUtils, id);
                        });
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.findById(id).stream()
                .findFirst()
                .ifPresentOrElse(productRepository::delete,
                        () -> {
                            throw new ProductNotFoundException(messagesUtils, id);
                        });
    }
}
