package springcloud.controller;

import org.example.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springcloud.service.DeptService;

import java.util.List;

//提供RESTFUL服务
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept){
        return deptService.addDept(dept);
    }
    @GetMapping ("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id){
        return deptService.queryById(id);
    }
    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptService.queryAll();
    }

    //注册进来的微服务~，获取一些信息
    @GetMapping("/dept/discovery")
    public Object discovery(){
        //获得微服务列表的清单
        List<String> services = discoveryClient.getServices();
        System.out.println("discovery=>services"+services);

        //得到一个具体的微服务信息,通过具体的微服务id，application-name
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRING-CLOUD-PROVIDER-DEPT");
        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost()+"\t"
                    +instance.getPort()+"\t"
                            +instance.getUri()+"\t"
                            +instance.getServiceId()
            );
        }
        return this.discoveryClient;
    }
}
