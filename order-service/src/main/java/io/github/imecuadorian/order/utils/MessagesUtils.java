package io.github.imecuadorian.order.utils;

import org.springframework.context.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class MessagesUtils {

    private final MessageSource messageSource;

    public MessagesUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    public String getMessage(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, Locale.getDefault());
    }
}
