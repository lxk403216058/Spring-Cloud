package org.example.service.impl;

import org.example.dao.ProductDao;
import org.example.pojo.Product;
import org.example.service.ProductService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


//暴露服务 注意这里使用的是dubbo提供的注解@Service,而不是Spring的
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
