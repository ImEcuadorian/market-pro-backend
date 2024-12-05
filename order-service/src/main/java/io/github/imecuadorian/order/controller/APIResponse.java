package io.github.imecuadorian.order.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
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
    public static <T> APIResponse<T> success(String message, T data, Map<String, Object> meta) {
        return new APIResponse<>("success", message, data, null, meta);
    }
    public static APIResponse<Void> error(String message, Map<String, String> errors) {
        return new APIResponse<>("error", message, null, errors, null);
    }
    public static APIResponse<Void> success(String message) {
        return new APIResponse<>("success", message, null, null, null);
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", errors=" + errors +
                ", meta=" + meta +
                '}';
    }
}
