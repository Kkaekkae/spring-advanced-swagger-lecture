package com.sparta.basic4.domain.repository;

import com.sparta.basic4.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
