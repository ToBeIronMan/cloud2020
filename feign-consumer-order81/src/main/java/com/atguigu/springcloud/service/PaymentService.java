package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-PAYMENT")
public interface PaymentService {

    /**
     * 要与提供方的controller里的方法保持一致
     * @param id
     */
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getCommonResultById(@PathVariable("id") Long id);
    @GetMapping(value="/payment/timeout")
    public String FeignTimeOut();
}

