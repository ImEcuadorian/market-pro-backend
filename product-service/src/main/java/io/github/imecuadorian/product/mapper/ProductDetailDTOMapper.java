package io.github.imecuadorian.product.mapper;

import io.github.imecuadorian.product.dto.CategoryDTO;
import io.github.imecuadorian.product.dto.ProductDetailDTO;
import io.github.imecuadorian.product.model.Product;
import io.github.imecuadorian.product.services.CategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
public class ProductDetailDTOMapper implements Mapper<Product, ProductDetailDTO> {

    private final CategoryService categoryService;

    public ProductDetailDTOMapper(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public ProductDetailDTO mapEntityToDTO(@NotNull Product product) {
        CategoryDTO category = categoryService.getCategoryById(product.getCategoryId());
        return new ProductDetailDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getSku(),
                product.getBrand(),
                product.getImagesUrl(),
                product.getWeight(),
                product.getColor(),
                product.getMadeIn(),
                product.getManufacturer(),
                category,
                product.getStatus()
        );
    }

    @Override
    public Product mapDTOToEntity(@NotNull ProductDetailDTO productDetailDTO) {
        return new Product(
                productDetailDTO.name(),
                productDetailDTO.description(),
                productDetailDTO.price(),
                productDetailDTO.stock(),
                productDetailDTO.sku(),
                productDetailDTO.brand(),
                productDetailDTO.imagesUrl(),
                productDetailDTO.weight(),
                productDetailDTO.color(),
                productDetailDTO.madeIn(),
                productDetailDTO.manufacturer(),
                productDetailDTO.category().id()
        );
    }
}
