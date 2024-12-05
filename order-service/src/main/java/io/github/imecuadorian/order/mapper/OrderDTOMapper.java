package io.github.imecuadorian.order.mapper;

import io.github.imecuadorian.order.dto.OrderDTO;
import io.github.imecuadorian.order.model.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class OrderDTOMapper implements Mapper<Order, OrderDTO> {

    @Override
    public OrderDTO mapEntityToDTO(@NotNull Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUserId());
        orderDTO.setItems(order.getItems());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderDate(order.getOrderDate());
        return orderDTO;
    }

    @Override
    public Order mapDTOToEntity(@NotNull OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUserId(orderDTO.getUserId());
        order.setItems(orderDTO.getItems());
        order.setStatus(orderDTO.getStatus());
        order.setOrderDate(orderDTO.getOrderDate());
        return order;
    }
}
