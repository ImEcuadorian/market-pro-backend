package io.github.imecuadorian.order.exception;

import io.github.imecuadorian.order.utils.MessagesUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
public class OrderValidationException extends RuntimeException {
    private static final String MESSAGE_KEY = "order.validation.error";
    private final Map<String, String> errors;
    public OrderValidationException(@NotNull MessagesUtils messagesUtils, Map<String, String> errors) {
        super(messagesUtils.getMessage(MESSAGE_KEY));
        this.errors = errors;
    }

}
