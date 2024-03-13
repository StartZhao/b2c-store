package com.startzhao.admin.service;

import com.startzhao.param.ProductSaveParam;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.utils.R;

import java.io.IOException;

/**
 * ClassName: AdminProductService
 * Package: com.startzhao.admin.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/12 23:03
 * @Version 1.0
 */
public interface AdminProductService {

    /**
     * 商品数据展示/搜索
     * @param productSearchParam
     * @return
     */
    R list(ProductSearchParam productSearchParam);

    /**
     * 保存商品
     * @param productSaveParam
     * @return
     */
    R save(ProductSaveParam productSaveParam) throws IOException;

    /**
     * 删除商品
     * @param productId
     * @return
     */
    R remove(Integer productId) throws IOException;

    /**
     * 更新商品
     * @param product
     * @return
     */
    R update(Product product) throws IOException;
}
