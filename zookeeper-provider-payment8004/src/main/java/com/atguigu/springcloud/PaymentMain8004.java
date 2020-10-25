package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * @author lucky
 * @author lucky
 * @author lucky
 */



//@SpringBootApplication

@SpringBootApplication
@EnableDiscoveryClient
/**
服务发现
 */
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);

    }
}
