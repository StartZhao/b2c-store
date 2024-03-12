package com.startzhao.admin.service.impl;

import com.startzhao.admin.service.AdminProductService;
import com.startzhao.clients.ProductClient;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: AdminProductServiceImpl
 * Package: com.startzhao.admin.service.impl
 * Description: 后台商品管理具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/12 23:05
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminProductServiceImpl implements AdminProductService {


    @Autowired
    private ProductClient productClient;
    /**
     * 商品数据展示/搜索
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R list(ProductSearchParam productSearchParam) {
        log.info("AdminProductServiceImpl.list业务结束，结果{}", "商品管理展示成功");
        return productClient.list(productSearchParam);
    }
}
