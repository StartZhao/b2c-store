package com.startzhao.order.controller;

import com.startzhao.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CartProductController
 * Package: com.startzhao.cart.controller
 * Description: 商品调用收藏服务
 *
 * @Author StartZhao
 * @Create 2024/3/13 10:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/order/product")
public class OrderProductController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/reference")
    public Boolean reference(@RequestBody Integer productId) {
        return orderService.reference(productId);
    }
}
