package io.github.imecuadorian.product.api;

import io.github.imecuadorian.product.utils.MessagesUtils;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
@Setter
public class APIResponse<T> {

    private String status;
    private String message;
    private T data;
    private Map<String, String> errors;
    private Map<String, Object> meta;
    public APIResponse(String status, String message, T data, Map<String, String> errors, Map<String, Object> meta) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.meta = meta;
    }

    @Contract("_, _, _, _ -> new")
    public static <T> @NotNull APIResponse<T> success(String message, T data, Map<String, Object> meta, @NotNull MessagesUtils messagesUtils) {
        return new APIResponse<>(messagesUtils.getMessage("status.success"), message, data, null, meta);
    }

    @Contract("_, _, _ -> new")
    public static @NotNull APIResponse<Void> error(String message, Map<String, String> errors, @NotNull MessagesUtils messagesUtils) {
        return new APIResponse<>(messagesUtils.getMessage("status.error"), message, null, errors, null);
    }

}
