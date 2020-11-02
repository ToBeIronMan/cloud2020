package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * @Description 模拟访问成功情况
     **/
    public String IsOk(){
        return "线程池："+Thread.currentThread().getName()+"，IsOk。";
    }

    /**
     * @Description 模拟访问失败情况
     **/
    public String IsTimeOut(int timeNumber){
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池："+Thread.currentThread().getName()+"，IsTimeOut，耗时(秒)"+timeNumber;
    }
}

