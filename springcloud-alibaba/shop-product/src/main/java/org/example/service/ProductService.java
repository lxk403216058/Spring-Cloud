package org.example.service;

import org.example.domain.Product;

public interface ProductService {
    //根据pid查询商品信息
    Product findByPid(Integer pid);
}
