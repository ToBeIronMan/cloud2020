package com.atguigu.springcloud.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author lucky
 */
@RestController
@Slf4j
public class OrderController {
    @Value("${payment.server.name}")
    private String path_url;
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/createPayment")
    public void  OrderController(){

    }
    @GetMapping("/get/{id}")
    public  void getPayment(@PathVariable("id")Long id)
    {

    }
    @GetMapping("test")
    public String Dopl()
    {
        return restTemplate.getForObject(path_url+"/test",String.class);
    }
}
