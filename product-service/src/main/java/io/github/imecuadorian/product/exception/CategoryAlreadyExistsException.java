package io.github.imecuadorian.product.exception;

import io.github.imecuadorian.product.utils.MessagesUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class CategoryAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE_KEY = "category.already.exists";

    private final String name;
    public CategoryAlreadyExistsException(@NotNull MessagesUtils messagesUtils, String name) {
        super(messagesUtils.getMessage(MESSAGE_KEY, name));
        this.name = name;
    }

}
