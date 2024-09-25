package com.sparta.basic4.application.service.order;

import com.sparta.basic4.application.dtos.order.CreateOrderRequest;
import com.sparta.basic4.application.dtos.order.OrderResponse;
import com.sparta.basic4.application.exceptions.NotFoundException;
import com.sparta.basic4.application.service.user.UserService;
import com.sparta.basic4.domain.Order;
import com.sparta.basic4.domain.OrderProduct;
import com.sparta.basic4.domain.User;
import com.sparta.basic4.domain.repository.OrderRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    public OrderService(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("주문이 존재하지 않습니다."));
        return OrderResponse.of(order);
    }

    @Transactional
    public OrderResponse createOrder(Long userId, CreateOrderRequest request) {
        User user = userService.getUser(userId);
        Order order = Order.create(
                user,
                request.getProducts().stream().map(product ->
                        OrderProduct.create(
                                product.getName(),
                                product.getPrice(),
                                product.getQuantity()))
                        .collect(Collectors.toList()));
        orderRepository.save(order);
        return OrderResponse.of(order);
    }
}
