package com.codecool.springshopapplication.repositories;

import com.codecool.springshopapplication.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
