package com.startzhao.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: GatewayApplication
 * Package: com.store.gateway
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/7 15:19
 * @Version 1.0
 */
@SpringBootApplication
@Slf4j
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        log.info("gateway started");
    }
}
