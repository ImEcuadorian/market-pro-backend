package io.github.imecuadorian.product.controller;

import io.github.imecuadorian.product.api.*;
import io.github.imecuadorian.product.exception.*;
import io.github.imecuadorian.product.utils.MessagesUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    private final MessagesUtils messagesUtils;

    public ExceptionHandlerController(MessagesUtils messagesUtils) {
        this.messagesUtils = messagesUtils;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleProductNotFound(@NotNull ProductNotFoundException ex) {
        Map<String, String> errors = Map.of("id", messagesUtils.getMessage("product.not.found", ex.getId()));
        APIResponse<Void> response = APIResponse.error(ex.getMessage(), errors, messagesUtils);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ProductValidationException.class)
    public ResponseEntity<APIResponse<Void>> handleProductValidationException(@NotNull ProductValidationException ex) {
        APIResponse<Void> response =
                APIResponse.error(ex.getMessage(), ex.getErrors(), messagesUtils);
        return ResponseEntity.badRequest().body(response);
    }


    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleCategoryNotFound(@NotNull CategoryNotFoundException ex) {
        Map<String, String> errors = Map.of("id", messagesUtils.getMessage("category.not.found", ex.getId()));
        APIResponse<Void> response = APIResponse.error(ex.getMessage(), errors, messagesUtils);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(CategoryValidationException.class)
    public ResponseEntity<APIResponse<Void>> handleCategoryValidationException(@NotNull CategoryValidationException ex) {
        APIResponse<Void> response =
                APIResponse.error(ex.getMessage(), ex.getErrors(), messagesUtils);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<APIResponse<Void>> handleCategoryAlreadyExistsException(@NotNull CategoryAlreadyExistsException ex) {
        Map<String, String> errors = Map.of("name", messagesUtils.getMessage("category.already.exists", ex.getName()));
        APIResponse<Void> response = APIResponse.error(ex.getMessage(), errors, messagesUtils);
        return ResponseEntity.badRequest().body(response);
    }
}
