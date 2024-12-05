package io.github.imecuadorian.order.services;

import io.github.imecuadorian.order.dto.OrderDTO;
import io.github.imecuadorian.order.model.Order;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();
    Order createOrder(Order order);

    OrderDTO getOrderById(String id);

    void cancelOrder(String id);

}
