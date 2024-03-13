package com.startzhao.admin.service.impl;

import com.startzhao.admin.service.AdminProductService;
import com.startzhao.clients.ProductClient;
import com.startzhao.param.ProductSaveParam;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    /**
     * 保存商品
     *
     * @param productSaveParam
     * @return
     */
    @Override
    public R save(ProductSaveParam productSaveParam) throws IOException {
        log.info("AdminProductServiceImpl.list业务结束，结果{}", "保存商品成功");
        return productClient.save(productSaveParam);
    }

    /**
     * 删除商品
     *
     * @param productId
     * @return
     */
    @Override
    @CacheEvict(value = "list.product", allEntries = true)
    public R remove(Integer productId) throws IOException {
        log.info("AdminProductServiceImpl.remove业务结束，结果{}", "删除商品成功");
        return productClient.remove(productId);
    }

    /**
     * 更新商品
     *
     * @param product
     * @return
     */
    @Override
    public R update(Product product) throws IOException {
        log.info("AdminProductServiceImpl.update业务结束，结果{}", "更新商品成功");
        return productClient.update(product);
    }
}
