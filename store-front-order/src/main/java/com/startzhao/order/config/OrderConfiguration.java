package com.startzhao.order.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: OrderConfiguration
 * Package: com.startzhao.config
 * Description: 订单配置类
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:04
 * @Version 1.0
 */
@Configuration
public class OrderConfiguration {


    /**
     * mq序列化方式，选择json！
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }
}

