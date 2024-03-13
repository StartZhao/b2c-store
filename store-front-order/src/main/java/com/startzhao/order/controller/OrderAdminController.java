package com.startzhao.order.controller;

import com.startzhao.order.service.OrderService;
import com.startzhao.param.PageParam;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: OrderAdminController
 * Package: com.startzhao.order.controller
 * Description: 后台调用订单相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/13 21:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/order/admin")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/list")
    public R adminList(@RequestBody PageParam pageParam){
        return orderService.adminList(pageParam);
    }

}
