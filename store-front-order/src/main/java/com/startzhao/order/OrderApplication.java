package com.startzhao.order;

import com.startzhao.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: OrderApplication
 * Package: com.startzhao.config
 * Description: 订单服务启动类
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:06
 * @Version 1.0
 */
@MapperScan(basePackages = "com.startzhao.order.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}

