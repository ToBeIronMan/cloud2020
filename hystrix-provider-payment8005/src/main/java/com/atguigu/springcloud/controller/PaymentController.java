package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/hystrix/ok")
    public String ok(){
        String result = paymentService.IsOk();
        log.info("*******result:"+result);
        return result;
    }
    @GetMapping("/hystrix/timeout")
    public String timeOut(){
        String result = paymentService.IsTimeOut(3);
        log.info("*******result:"+result);
        return result;
    }
}

