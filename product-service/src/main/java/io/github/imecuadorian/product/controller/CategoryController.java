package io.github.imecuadorian.product.controller;

import io.github.imecuadorian.product.api.APIResponse;
import io.github.imecuadorian.product.dto.CategoryDTO;
import io.github.imecuadorian.product.exception.CategoryAlreadyExistsException;
import io.github.imecuadorian.product.exception.CategoryValidationException;
import io.github.imecuadorian.product.model.Category;
import io.github.imecuadorian.product.services.CategoryService;
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
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private static String message = "";
    private final CategoryService categoryService;
    private final MessagesUtils messagesUtils;

    public CategoryController(CategoryService categoryService, MessagesUtils messagesUtils) {
        this.categoryService = categoryService;
        this.messagesUtils = messagesUtils;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<CategoryDTO>>> getCategories() {
        message = messagesUtils.getMessage("categories.retrieved.success");
        APIResponse<List<CategoryDTO>> response = APIResponse.success(message, categoryService.getCategories(), null, messagesUtils);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryDTO>> getCategoryById(@PathVariable String id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        message = messagesUtils.getMessage("category.retrieved.success");
        APIResponse<CategoryDTO> response = APIResponse.success(message, category, null, messagesUtils);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Category>> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrorResult(bindingResult);
            throw new CategoryValidationException(messagesUtils, errors);
        }

        if (categoryService.existsByName(category.getName())) {
            throw new CategoryAlreadyExistsException(messagesUtils, category.getName());
        }

        Category createdCategory = categoryService.createCategory(category);
        message = messagesUtils.getMessage("category.creation.success", category.getName());
        APIResponse<Category> response = APIResponse.success(message, createdCategory, null, messagesUtils);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<CategoryDTO>> updateCategory(@PathVariable String id, @Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrorResult(bindingResult);
            throw new CategoryValidationException(messagesUtils, errors);
        }
        categoryService.updateCategoryById(id, category);
        APIResponse<CategoryDTO> response = APIResponse.success(messagesUtils.getMessage("category.update.success"), categoryService.getCategoryById(id), null, messagesUtils);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        APIResponse<Void> response = APIResponse.success(messagesUtils.getMessage("category.deletion.success"), null, null, messagesUtils);
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
