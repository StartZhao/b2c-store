package com.startzhao.statics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: StaticsOssAplication
 * Package: com.startzhao.statics
 * Description: 静态资源服务启动类
 *
 * @Author StartZhao
 * @Create 2024/3/8 16:34
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
public class StaticsOssAplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticsOssAplication.class, args);
        log.info("statics started");
    }
}
