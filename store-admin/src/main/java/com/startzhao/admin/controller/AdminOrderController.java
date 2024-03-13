package com.startzhao.admin.controller;

import com.startzhao.admin.service.AdminOrderService;
import com.startzhao.param.PageParam;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: AdminOrderController
 * Package: com.startzhao.admin.controller
 * Description: 后台订单管理相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/13 21:11
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class AdminOrderController {

    @Autowired
    private AdminOrderService adminOrderService;


    @GetMapping("/list")
    public R list(PageParam pageParam) {
        return adminOrderService.list(pageParam);
    }
}
