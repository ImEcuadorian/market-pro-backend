package io.github.imecuadorian.product.exception;

import io.github.imecuadorian.product.utils.MessagesUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
public class ProductValidationException extends RuntimeException {

    private static final String MESSAGE_KEY = "product.validation.error";
    private final Map<String, String> errors;
    public ProductValidationException(@NotNull MessagesUtils messagesUtils, Map<String, String> errors) {
        super(messagesUtils.getMessage(MESSAGE_KEY));
        this.errors = errors;
    }

}
