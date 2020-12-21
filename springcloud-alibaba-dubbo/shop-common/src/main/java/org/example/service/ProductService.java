package org.example.service;

import org.example.pojo.Product;

public interface ProductService {
    Product findByPid(Integer pid);
}
