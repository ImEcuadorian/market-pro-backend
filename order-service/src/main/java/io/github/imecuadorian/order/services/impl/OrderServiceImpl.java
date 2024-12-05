package io.github.imecuadorian.order.services.impl;

import io.github.imecuadorian.order.clients.ProductFeignClient;
import io.github.imecuadorian.order.dto.OrderDTO;
import io.github.imecuadorian.order.dto.products.ProductDetailDTO;
import io.github.imecuadorian.order.exception.OrderNotFoundException;
import io.github.imecuadorian.order.mapper.OrderDTOMapper;
import io.github.imecuadorian.order.model.Order;
import io.github.imecuadorian.order.repository.OrderRepository;
import io.github.imecuadorian.order.services.OrderService;
import io.github.imecuadorian.order.utils.MessagesUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final MessagesUtils messagesUtils;
    private final OrderRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;

    private final ProductFeignClient productFeignClient;

    public OrderServiceImpl(MessagesUtils messagesUtils, OrderRepository orderRepository, OrderDTOMapper orderDTOMapper, ProductFeignClient productFeignClient) {
        this.messagesUtils = messagesUtils;
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
        this.productFeignClient = productFeignClient;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderDTOMapper::mapEntityToDTO)
                .toList();
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderDTO getOrderById(String id) {
       return orderRepository.findById(id)
               .map(orderDTOMapper::mapEntityToDTO)
               .orElseThrow(() -> new OrderNotFoundException(messagesUtils, id));
    }

    @Override
    public void cancelOrder(String id) {
        orderRepository.findById(id).ifPresentOrElse(orderRepository::delete, () -> {
            throw new OrderNotFoundException(messagesUtils, id);
        });
    }
}
