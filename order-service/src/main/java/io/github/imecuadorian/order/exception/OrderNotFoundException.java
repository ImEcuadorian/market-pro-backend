package io.github.imecuadorian.order.exception;

import io.github.imecuadorian.order.utils.MessagesUtils;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class OrderNotFoundException extends RuntimeException {

    private static final String MESSAGE_KEY = "order.not.found";

    private final String id;
    public OrderNotFoundException(@NotNull MessagesUtils messagesUtils, String id) {
        super(messagesUtils.getMessage(MESSAGE_KEY, id));
        this.id = id;
    }

}
