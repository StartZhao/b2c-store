package com.startzhao.product.service;

import com.startzhao.param.ProductPromo;
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
}
