package com.startzhao.product.service;

import com.startzhao.param.ProductByCategoryParam;
import com.startzhao.param.ProductHotsParam;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.utils.R;

import java.util.List;

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

    /**
     * 查询所有商品
     * @return 返回商品集合
     */
    List<Product> listProduct();

    /**
     * 商品搜索
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 根据商品 ids 得到商品数据
     * @param ids
     * @return
     */
    List<Product> listProductByIds(List<Integer> ids);
}
