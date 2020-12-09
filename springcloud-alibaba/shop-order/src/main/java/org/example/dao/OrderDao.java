package org.example.dao;

import org.example.domain.Order;
import org.example.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order, Integer> {
}
