package io.github.imecuadorian.order.dto;

import io.github.imecuadorian.order.model.OrderItem;
import io.github.imecuadorian.order.model.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

    private String id;
    private String userId;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private OrderStatus status;

    public OrderDTO(String id, String userId, List<OrderItem> items, LocalDateTime orderDate, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.orderDate = orderDate;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDTO orderDTO)) return false;
        return Objects.equals(getId(), orderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
