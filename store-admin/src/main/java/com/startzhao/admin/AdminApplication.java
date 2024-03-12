package com.startzhao.admin;

import com.startzhao.clients.CategoryClient;
import com.startzhao.clients.ProductClient;
import com.startzhao.clients.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ClassName: AdminApplication
 * Package: com.startzhao.admin.config
 * Description: 后台管理服务启动类
 *
 * @Author StartZhao
 * @Create 2024/3/11 23:01
 * @Version 1.0
 */
@MapperScan("com.startzhao.admin.mapper")
@SpringBootApplication
@EnableCaching //开启缓存支持
@EnableFeignClients(clients = {CategoryClient.class, UserClient.class, ProductClient.class})
public class AdminApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}

