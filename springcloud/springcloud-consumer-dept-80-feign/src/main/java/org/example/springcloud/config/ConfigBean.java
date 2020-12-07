package org.example.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean { // @configuration -- spring applicationContext.xml
    //配置负载均衡实现RestTemplate
    //IRule
    //AvailabilityFilteringRule：会过滤掉访问故障的服务~剩下的轮询
    //RetryRule：
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

   /* @Bean
    public IRule myRule(){
        return new LxkRandomRule();
    }*/
}
