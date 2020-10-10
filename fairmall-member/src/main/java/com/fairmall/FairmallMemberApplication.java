package com.fairmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Version 1.0
 * @Author ZHANGBAIFA
 * @Date 2020/10/9 15:33
 * @Description:
 *  1、想要远程调用别的服务
 *      1）、引入open-feign
 *      2）、编写一个接口，告诉SpringCloud这个接口需要调用远程服务
 *  2、声明接口的每一个方法都是调用哪个远程服务的那个请求
 *      3）、开启远程调用功能
 */
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
public class FairmallMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(FairmallMemberApplication.class, args);
    }
}
