package com.startzhao.order.controller;

import com.startzhao.order.service.OrderService;
import com.startzhao.param.OrderParam;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: OrderController
 * Package: com.startzhao.controller
 * Description: 订单相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:07
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/save")
    public R save(@RequestBody OrderParam orderParam) {
        return orderService.save(orderParam);
    }


    @PostMapping("/list")
    public R list(@RequestBody Map<String,Integer>userIdMap) {
        return orderService.list(userIdMap.get("user_id"));
    }

}
