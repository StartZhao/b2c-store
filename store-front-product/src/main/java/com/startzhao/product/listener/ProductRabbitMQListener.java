package com.startzhao.product.listener;

import com.startzhao.product.service.ProductService;
import com.startzhao.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: ProductRabbitMQListener
 * Package: com.startzhao.product.listener
 * Description: 商品的mq消息监听
 *
 * @Author StartZhao
 * @Create 2024/3/11 12:44
 * @Version 1.0
 */
@Component
public class ProductRabbitMQListener {

    @Autowired
    private ProductService productService;


    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange("topic.ex"),
                    value = @Queue(name = "sub.queue"),
                    key = "sub.number"
            )
    )
    public void subNumber(List<OrderToProduct> orderToProducts) {
        productService.subNumber(orderToProducts);

    }


}
