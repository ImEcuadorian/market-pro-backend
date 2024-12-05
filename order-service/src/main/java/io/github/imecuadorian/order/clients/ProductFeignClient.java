package io.github.imecuadorian.order.clients;

import io.github.imecuadorian.order.controller.APIResponse;
import io.github.imecuadorian.order.dto.products.ProductDetailDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/api/v1/products/{id}")
    APIResponse<ProductDetailDTO> getProductById(@PathVariable("id") String id);

}
