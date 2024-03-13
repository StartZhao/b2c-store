package com.startzhao.cart.controller;

import com.startzhao.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CartProductController
 * Package: com.startzhao.cart.controller
 * Description: 商品调用购物车服务
 *
 * @Author StartZhao
 * @Create 2024/3/13 10:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/cart/product")
public class CartProductController {


    @Autowired
    private CartService cartService;

    @PostMapping("/reference")
    public Boolean reference(@RequestBody Integer productId) {
        return cartService.reference(productId);
    }
}
