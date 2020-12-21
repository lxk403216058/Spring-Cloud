package org.example.service;

import org.example.dao.ProductDao;
import org.example.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }

    @Override
    public void reduceInventory(Integer pid, Integer number) {
        //查询
        Product product = productDao.findById(pid).get();
        //省略校验
        //内存中扣减
        product.setStock(product.getStock()-number);
        //模拟异常


        //保存
        productDao.save(product);
    }
}
