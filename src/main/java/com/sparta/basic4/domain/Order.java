package com.sparta.basic4.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true)
    List<OrderProduct> products;
    @CreatedDate
    LocalDateTime createdAt;


    public static Order create(User user, List<OrderProduct> products) {
        Order order = Order.builder()
                .user(user)
                .build();

        order.updateProducts(products);

        return order;
    }

    public void updateProducts(List<OrderProduct> orderProducts) {
        this.products.clear();
        this.products = orderProducts;
    }

    public Long getTotalPrice() {
        return this.products.stream().mapToLong(OrderProduct::getPrice).sum();
    }

    public Integer getTotalQuantity() {
        return this.products.stream().mapToInt(OrderProduct::getQuantity).sum();
    }
}
