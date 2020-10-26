package com.atguigu.springcloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Value("${cs.server.url}")
    private String csUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String payment() {
        String result = restTemplate.getForObject(csUrl + "/payment/consul", String.class);
        return result;

    }
}
