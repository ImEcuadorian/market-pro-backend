package io.github.imecuadorian.product.controller;

import io.github.imecuadorian.product.api.APIResponse;
import io.github.imecuadorian.product.dto.ProductDTO;
import io.github.imecuadorian.product.dto.ProductDetailDTO;
import io.github.imecuadorian.product.exception.ProductValidationException;
import io.github.imecuadorian.product.model.Product;
import io.github.imecuadorian.product.services.ProductService;
import io.github.imecuadorian.product.utils.MessagesUtils;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final MessagesUtils messagesUtils;

    public ProductController(ProductService productService, MessagesUtils messagesUtils) {
        this.productService = productService;
        this.messagesUtils = messagesUtils;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<ProductDTO>>> getProducts() {
        APIResponse<List<ProductDTO>> response =
                APIResponse.success(messagesUtils.getMessage("products.retrieved.success"), productService.getProducts(), null, messagesUtils);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProductDetailDTO>> getProductById(@PathVariable String id) {
        ProductDetailDTO product = productService.getProductById(id);

        APIResponse<ProductDetailDTO> response =
                APIResponse.success(messagesUtils.getMessage("product.retrieved.success"), product, null, messagesUtils);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Product>> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrorResult(bindingResult);
            throw new ProductValidationException(messagesUtils, errors);
        }
        Product createdProduct = productService.createProduct(product);
        APIResponse<Product> response =
                APIResponse.success(messagesUtils.getMessage("product.creation.success"), createdProduct, null, messagesUtils);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<ProductDetailDTO>> updateProduct(@PathVariable String id, @Valid @RequestBody Product product, BindingResult bindingResult) {
        ProductDetailDTO productDTO = productService.getProductById(id);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrorResult(bindingResult);
            throw new ProductValidationException(messagesUtils, errors);
        }
        product.setId(id);
        productService.createProduct(product);
        APIResponse<ProductDetailDTO> response =
                APIResponse.success(messagesUtils.getMessage("product.update.success"), productDTO, null, messagesUtils);
        return ResponseEntity.ok(response);
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<APIResponse<Void>> updateStatus(@PathVariable String id, @RequestBody Map<String, String> body) {
        String status = body.get("status");
        productService.updateProductStatus(id, status);
        APIResponse<Void> response = APIResponse.success(messagesUtils.getMessage("product.status.updated"), null, null, messagesUtils);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        APIResponse<Void> response = APIResponse.success(messagesUtils.getMessage("product.deletion.success"), null, null, messagesUtils);
        return ResponseEntity.ok(response);
    }

    @Nullable
    private Map<String, String> getErrorResult(@NotNull BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return errors;
        }
        return null;
    }

}
