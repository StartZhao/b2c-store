package com.startzhao.product.service;

import com.startzhao.param.ProductByCategoryParam;
import com.startzhao.param.ProductHotsParam;
import com.startzhao.utils.R;

/**
 * ClassName: ProductService
 * Package: com.startzhao.product.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:44
 * @Version 1.0
 */
public interface ProductService {

    /**
     * 根据首页类别显示商品 最多显示 7 件商品
     * @param categoryName
     * @return
     */
    R promo(String categoryName);

    /**
     * 多热门类别商品查询，最多查询 7 条商品
     * @param productHotsParam
     * @return
     */
    R hots(ProductHotsParam productHotsParam);

    /**
     * 类别信息查询
     * @return
     */
    R list();

    /**
     * 根据条件获取分类数据
     * @param productByCategoryParam
     * @return
     */
    R byCategory(ProductByCategoryParam productByCategoryParam);

    /**
     * 根据商品 id 查询商品数据
     * @param productId
     * @return
     */
    R detail(Integer productId);

    /**
     * 根据商品 id 查询商品图像
     * @param productId
     * @return
     */
    R pictures(Integer productId);
}
