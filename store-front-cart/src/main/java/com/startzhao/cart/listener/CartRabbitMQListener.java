package com.startzhao.cart.listener;

import com.startzhao.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName: CartRabbitMQListener
 * Package: com.startzhao.cart.listener
 * Description: 订单消息监听
 *
 * @Author StartZhao
 * @Create 2024/3/11 16:55
 * @Version 1.0
 */
@Component
public class CartRabbitMQListener {


    @Autowired
    private CartService cartService;


    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange("topic.ex"),
                    value = @Queue(name = "clear.queue"),
                    key = "clear.cart"
            )
    )
    public void clearCart(List<Integer> cartIds) {
        cartService.clearCart(cartIds);
    }
}
