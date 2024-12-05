package io.github.imecuadorian.product.exception;

import io.github.imecuadorian.product.utils.MessagesUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
public class CategoryValidationException extends RuntimeException {

    private static final String MESSAGE_KEY = "category.validation.error";
    private final Map<String, String> errors;
    public CategoryValidationException(@NotNull MessagesUtils messagesUtils, Map<String, String> errors) {
        super(messagesUtils.getMessage(MESSAGE_KEY));
        this.errors = errors;
    }

}
