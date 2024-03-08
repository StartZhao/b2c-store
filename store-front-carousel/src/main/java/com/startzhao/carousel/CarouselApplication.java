package com.startzhao.carousel;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: CarouselApplication
 * Package: com.startzhao.carousel
 * Description: 轮播图服务启动类
 *
 * @Author StartZhao
 * @Create 2024/3/8 16:53
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = "com.startzhao.carousel.mapper")
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class, args);
        log.info("carousel started");
    }
}
