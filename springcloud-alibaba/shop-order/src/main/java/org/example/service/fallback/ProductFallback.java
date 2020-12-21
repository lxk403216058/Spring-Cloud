package org.example.service.fallback;

import org.example.domain.Product;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

//这是一个容错类，需要实现feign所在的接口，并去实现线接口类中的所有方法
//一旦feign远程调用出现问题了，就会进入档期那类中的同名方法，执行容错逻辑
//@Service
public class ProductFallback implements ProductService {
    @Override
    public Product findByPid(Integer pid) {
        //容错逻辑
        Product product = new Product();
        product.setPid(-100)
                .setPname("远程调用商品为微服务出现异常，进入了容错逻辑");

        return product;
    }

    @Override
    public void reduceInventory(Integer pid, Integer number) {

    }
}
