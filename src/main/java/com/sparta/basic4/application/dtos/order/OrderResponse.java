package com.sparta.basic4.application.dtos.order;

import com.sparta.basic4.domain.Order;
import com.sparta.basic4.domain.OrderProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class OrderResponse {
    Long orderId;
    String username;
    Long totalPrice;
    Integer totalQuantity;
    List<OrderProductResponse> products;
    LocalDateTime orderDate;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(access = AccessLevel.PRIVATE)
    public static class OrderProductResponse {
        Long productId;
        String name;
        Long price;
        Integer quantity;

        public static OrderProductResponse of(OrderProduct product) {
            return OrderProductResponse.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build();
        }
    }

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .orderId(order.getId())
                .username(order.getUser().getUsername())
                .totalPrice(order.getTotalPrice())
                .totalQuantity(order.getTotalQuantity())
                .products(order.getProducts().stream().map(OrderProductResponse::of).collect(Collectors.toList()))
                .build();
    }

}
