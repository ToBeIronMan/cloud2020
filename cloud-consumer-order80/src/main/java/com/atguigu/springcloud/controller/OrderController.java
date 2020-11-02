package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author lucky
 */
@RestController
@Slf4j
public class OrderController {
    public static  final String path_url="http://CLOUD-PROVIDER-PAYMENT/payment";
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/consumer/createPayment")
    public CommonResult<Payment> create(Payment payment){
        return  restTemplate.postForObject(path_url+"/create",payment,CommonResult.class);
    }
    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id)
    {
        return restTemplate.getForObject(path_url+"/get/"+id,CommonResult.class);
    }
    @GetMapping("/get2/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id")Long id)
    {
        ResponseEntity<CommonResult> entity =restTemplate.getForEntity(path_url+"/get/"+id,CommonResult.class);
        /**
         * * getForObject为返回响应内容转为为的对象
         *   getForEntity为返回的对象为ResponseEntity，包括响应头，响应状态码，响应体
         */
        if(entity.getStatusCode().is2xxSuccessful()){
        return  entity.getBody();
        }
        else{

            return new  CommonResult<>(444,"操作失败");
        }

    }

    @GetMapping("test")
    public String Dopl(){
        return restTemplate.getForObject(path_url+"/test",String.class);
    }
    @GetMapping("/dest")
    public String getPaymentLb(){
        List<ServiceInstance> serviceInstances=discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        if(serviceInstances==null||serviceInstances.size()<=0)
        {
            return  null;

        }
        ServiceInstance instance=loadBalancer.instance(serviceInstances);
        URI uri=instance.getUri();
        return restTemplate.getForObject(uri+"/payment/test",String.class);
    }
}
