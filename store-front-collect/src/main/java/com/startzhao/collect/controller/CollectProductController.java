package com.startzhao.collect.controller;

import com.startzhao.collect.service.CollectService;
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
@RequestMapping("/collect/product")
public class CollectProductController {


    @Autowired
    private CollectService collectService;

    @PostMapping("/reference")
    public Boolean reference(@RequestBody Integer productId) {
        return collectService.reference(productId);
    }
}
