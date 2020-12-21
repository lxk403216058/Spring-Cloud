package org.example.dao;

import org.example.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Long> {
}
