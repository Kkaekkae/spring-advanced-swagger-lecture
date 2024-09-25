package com.sparta.basic4.userInterface;

import com.sparta.basic4.application.dtos.auth.UserDetailsImpl;
import com.sparta.basic4.application.dtos.order.CreateOrderRequest;
import com.sparta.basic4.application.dtos.order.OrderResponse;
import com.sparta.basic4.application.service.order.OrderService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("{orderId}")
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable Long orderId) {
        OrderResponse order = orderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> updateOrder(
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @RequestBody CreateOrderRequest request) {
        OrderResponse order = orderService.createOrder(loginUser.getUser().getId(), request);
        return ResponseEntity.ok(order);
    }
}
