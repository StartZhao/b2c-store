package com.startzhao.product.config;

import com.startzhao.config.CacheConfiguration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: ProductConfiguration
 * Package: com.startzhao.product.config
 * Description: 商品服务配置类
 *
 * @Author StartZhao
 * @Create 2024/3/9 17:03
 * @Version 1.0
 */
@Configuration
public class ProductConfiguration extends CacheConfiguration {

    /**
     * mq序列化方式，选择json！
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){

        return new Jackson2JsonMessageConverter();
    }
}
