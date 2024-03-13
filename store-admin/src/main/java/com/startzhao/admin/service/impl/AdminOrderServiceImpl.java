package com.startzhao.admin.service.impl;

import com.startzhao.admin.service.AdminOrderService;
import com.startzhao.clients.OrderClient;
import com.startzhao.param.PageParam;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * ClassName: AdminOrderServiceImpl
 * Package: com.startzhao.admin.service.impl
 * Description: 后台订单管理服务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/13 21:12
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminOrderServiceImpl implements AdminOrderService {


    @Autowired
    private OrderClient orderClient;

    /**
     * 订单展示功能
     *
     * @param pageParam
     * @return
     */
    @Override
    @Cacheable(value = "list.order", key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    public R list(PageParam pageParam) {
        return orderClient.adminList(pageParam);
    }
}
