package com.startzhao.cart;

import com.startzhao.clients.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * ClassName: CartApplication
 * Package: com.startzhao.cart
 * Description: 购物车服务启动类
 *
 * @Author StartZhao
 * @Create 2024/3/10 19:36
 * @Version 1.0
 */
@SpringBootApplication
@Slf4j
@MapperScan(basePackages = "com.startzhao.cart.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
        log.info("cart started");
    }
}
