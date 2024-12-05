package io.github.imecuadorian.product.exception;

import io.github.imecuadorian.product.utils.MessagesUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class CategoryNotFoundException extends RuntimeException {

    private static final String MESSAGE_KEY = "category.not.found";
    private final String id;
    public CategoryNotFoundException(@NotNull MessagesUtils messagesUtils, String id) {
        super(messagesUtils.getMessage(MESSAGE_KEY, id));
        this.id = id;
    }

}
