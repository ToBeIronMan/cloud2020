package com.atguigu.springcloud;

import com.atguigu.myrule.mySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author lucky
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="CLOUD-PROVIDER-PAYMENT",configuration = mySelfRule.class)
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class,args);
    }
}
