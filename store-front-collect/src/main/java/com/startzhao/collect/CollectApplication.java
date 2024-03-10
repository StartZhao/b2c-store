package com.startzhao.collect;

import com.startzhao.clients.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: CollectApplication
 * Package: com.startzhao.collect
 * Description: 收藏服务启动类
 *
 * @Author StartZhao
 * @Create 2024/3/10 10:22
 * @Version 1.0
 */
@SpringBootApplication
@Slf4j
@EnableFeignClients(clients = ProductClient.class)
@MapperScan(basePackages = "com.startzhao.collect.mapper")
public class CollectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class, args);
        log.info("collect started");
    }
}
