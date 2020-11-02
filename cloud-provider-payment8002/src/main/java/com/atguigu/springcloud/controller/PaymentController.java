package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lucky
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentSerice;
    @GetMapping("test")
    public String doPo(){
        return "8002";
    }
    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result =paymentSerice.create(payment);
        log.info("******插入结果"+result);
        if(result>0) {
            return new  CommonResult(200,"插入成功",result);
        } else {
            return new CommonResult(444,"插入失败");
        }
    }
    @GetMapping("/get/{id}")
    public CommonResult getCommonResultById(@PathVariable("id")Long id){
        Payment result=paymentSerice.getPaymentById(id);
        log.info("****查询结果"+result);
        if(result!=null)
        {
            return new CommonResult(200,"成功了",result);
        }
        else
        {
            return new CommonResult(444,"失败了");
        }

    }
}
