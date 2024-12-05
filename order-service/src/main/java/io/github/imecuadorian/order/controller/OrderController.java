package io.github.imecuadorian.order.controller;

import io.github.imecuadorian.order.dto.OrderDTO;
import io.github.imecuadorian.order.exception.OrderValidationException;
import io.github.imecuadorian.order.model.Order;
import io.github.imecuadorian.order.services.OrderService;
import io.github.imecuadorian.order.utils.MessagesUtils;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final MessagesUtils messagesUtils;

    public OrderController(OrderService orderService, MessagesUtils messagesUtils) {
        this.orderService = orderService;
        this.messagesUtils = messagesUtils;
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<OrderDTO>>> getProducts() {
        APIResponse<List<OrderDTO>> response =
                APIResponse.success(messagesUtils.getMessage("orders.retrieved.success"), orderService.getAllOrders(), null);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<OrderDTO>> getProductById(@PathVariable String id) {
        OrderDTO order = orderService.getOrderById(id);

        APIResponse<OrderDTO> response =
                APIResponse.success(messagesUtils.getMessage("order.retrieved.success"), order, null);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<APIResponse<Order>> createProduct(@Valid @RequestBody Order order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrorResult(bindingResult);
            throw new OrderValidationException(messagesUtils, errors);
        }
        Order createdProduct = orderService.createOrder(order);
        APIResponse<Order> response =
                APIResponse.success(messagesUtils.getMessage("order.creation.success"), createdProduct, null);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<OrderDTO>> updateProduct(@PathVariable String id, @Valid @RequestBody Order order, BindingResult bindingResult) {
        OrderDTO productDTO = orderService.getOrderById(id);
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = getErrorResult(bindingResult);
            throw new OrderValidationException(messagesUtils, errors);
        }
        order.setId(id);
        orderService.createOrder(order);
        APIResponse<OrderDTO> response =
                APIResponse.success(messagesUtils.getMessage("order.update.success"), productDTO, null);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Void>> deleteProduct(@PathVariable String id) {
        orderService.cancelOrder(id);
        APIResponse<Void> response = APIResponse.success(messagesUtils.getMessage("order.deletion.success"), null, null);
        return ResponseEntity.ok(response);
    }

    @Nullable
    private Map<String, String> getErrorResult(@NotNull BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return errors;
        }
        return null;
    }

}
