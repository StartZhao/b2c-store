package com.startzhao.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.startzhao.param.OrderParam;
import com.startzhao.param.PageParam;
import com.startzhao.pojo.Orders;
import com.startzhao.utils.R;

/**
 * ClassName: OrderService
 * Package: com.startzhao.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:08
 * @Version 1.0
 */
public interface OrderService extends IService<Orders> {

    /**
     * 订单生成
     * @param orderParam
     * @return
     */
    R save(OrderParam orderParam);

    /**
     * 订单展示业务
     * @param userId
     */
    R list(Integer userId);

    /**
     * 订单是否引用商品
     * @param productId
     * @return
     */
    Boolean reference(Integer productId);


    /**
     * 后台订单展示功能
     * @param pageParam
     * @return
     */
    R adminList(PageParam pageParam);
}
