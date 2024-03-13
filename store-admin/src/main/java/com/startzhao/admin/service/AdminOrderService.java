package com.startzhao.admin.service;

import com.startzhao.param.PageParam;
import com.startzhao.utils.R;

/**
 * ClassName: AdminOrderService
 * Package: com.startzhao.admin.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/13 21:12
 * @Version 1.0
 */
public interface AdminOrderService {

    /**
     * 订单展示功能
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);
}
