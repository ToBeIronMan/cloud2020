package com.atguigu.springcloud.controller;


import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author lucky
 */
@RestController
@Slf4j
public class OrderController {
    public static  final String path_url="http://localhost:8001/payment";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/createPayment")
    public CommonResult<Payment> create(Payment payment){
        return  restTemplate.postForObject(path_url+"/create",payment,CommonResult.class);
    }
    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id)
    {
        return restTemplate.getForObject(path_url+"/get/"+id,CommonResult.class);
    }
}
