package io.github.imecuadorian.order.controller;

import io.github.imecuadorian.order.exception.OrderNotFoundException;
import io.github.imecuadorian.order.exception.OrderValidationException;
import io.github.imecuadorian.order.utils.MessagesUtils;
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

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<APIResponse<Void>> handleProductNotFound(@NotNull OrderNotFoundException ex) {
        Map<String, String> errors = Map.of("id", messagesUtils.getMessage("order.not.found", ex.getId()));
        APIResponse<Void> response = APIResponse.error(ex.getMessage(), errors);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(OrderValidationException.class)
    public ResponseEntity<APIResponse<Void>> handleProductValidationException(@NotNull OrderValidationException ex) {
        APIResponse<Void> response =
                APIResponse.error(ex.getMessage(), ex.getErrors());
        return ResponseEntity.badRequest().body(response);
    }

}
