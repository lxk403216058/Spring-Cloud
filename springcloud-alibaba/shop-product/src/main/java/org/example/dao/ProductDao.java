package org.example.dao;

import org.example.domain.Product;
import org.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {
}
