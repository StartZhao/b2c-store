package com.startzhao.cart.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * ClassName: CartConfiguration
 * Package: com.startzhao.cart.config
 * Description: 购物车配置类
 *
 * @Author StartZhao
 * @Create 2024/3/11 16:54
 * @Version 1.0
 */
@SpringBootConfiguration
public class CartConfiguration {

    /**
     * mq序列化方式，选择json！
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }
}
