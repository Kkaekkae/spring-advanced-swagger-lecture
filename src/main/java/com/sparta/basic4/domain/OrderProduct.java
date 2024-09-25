package com.sparta.basic4.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;
    private String name;
    private Long price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public static OrderProduct create(String name, Long price, Integer quantity) {
        return OrderProduct.builder()
                .name(name)
                .price(price)
                .quantity(quantity)
                .build();
    }

    public void update(String name, Long price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
