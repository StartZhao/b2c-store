package com.startzhao.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.startzhao.param.ProductByCategoryParam;
import com.startzhao.param.ProductHotsParam;
import com.startzhao.param.ProductSaveParam;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.to.OrderToProduct;
import com.startzhao.utils.R;

import java.io.IOException;
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
public interface ProductService extends IService<Product> {

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
    R listCategory();

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

    /**
     * 修改库存，增加销量
     * @param orderToProducts
     */
    void subNumber(List<OrderToProduct> orderToProducts);

    /**
     * 判断类别是否被商品绑定
     * @param categoryId
     * @return
     */
    boolean exist(Integer categoryId);

    /**
     * 保存商品和图片数据
     * @param productSaveParam
     * @return
     */
    R saveDetail(ProductSaveParam productSaveParam) throws IOException;

    /**
     * 删除商品以及对应的图片数据
     * @param productId
     * @return
     */
    R removeDetail(Integer productId) throws IOException;

    /**
     * 更新商品数据
     * @param product
     * @return
     */
    R update(Product product) throws IOException;
}
