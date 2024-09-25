package com.sparta.basic4.application.dtos.order;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    @NotNull
    List<OrderProductRequest> products;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderProductRequest {
        @NotBlank
        String name;
        @NotBlank
        Long price;
        @NotBlank
        Integer quantity;
    }
}
