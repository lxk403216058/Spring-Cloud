package org.example.service;

import org.example.domain.Product;
import org.example.service.fallback.ProductFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//Product product = restTemplate.getForObject("http://service-product/product/"+pid, Product.class);
//value用于指定调用nacos下的哪个微服务
//fallback用于指定当前feign接口的容错类, fallbackFactory = ProductFallbackFactory.class
@FeignClient(value = "service-product")
public interface ProductService {

    //FeignClient的value+@RequestMapping的value值，其实就是一个完整的请求地址 http://service-product/product/"+pid
    //指定请求的uri部分
    @RequestMapping("/product/{pid}")
    public Product findByPid(@PathVariable("pid") Integer pid);

    //扣减库存的方法
    //参数一：商品标识
    //参数二：扣减数量
    @RequestMapping("/product/reduceInventory")
    void reduceInventory(@RequestParam("pid") Integer pid, @RequestParam("number") Integer number);
}
