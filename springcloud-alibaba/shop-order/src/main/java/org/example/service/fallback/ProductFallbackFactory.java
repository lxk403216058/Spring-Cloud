package org.example.service.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Product;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

//这是一个容错的工厂类，要求我们实现一个FallbackFactory<要为那个接口产生容错类>接口
@Slf4j
//@Service
public class ProductFallbackFactory implements FallbackFactory<ProductService> {

    //Throwable这就是feign在调用过程中产生的异常
    @Override
    public ProductService create(Throwable throwable) {

        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                log.error("{}",throwable);
                //容错逻辑
                Product product = new Product();
                product.setPid(-100)
                        .setPname("远程调用商品为微服务出现异常，进入了容错逻辑");

                return product;
            }

            @Override
            public void reduceInventory(Integer pid, Integer number) {

            }
        };
    }
}
