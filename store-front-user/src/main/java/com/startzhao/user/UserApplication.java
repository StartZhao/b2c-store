package com.startzhao.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: UserApplication
 * Package: com.startzhao.user
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/7 19:10
 * @Version 1.0
 */

@SpringBootApplication
@Slf4j
@MapperScan(basePackages = "com.startzhao.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        log.info("user started");
    }
}
