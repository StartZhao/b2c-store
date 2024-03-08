package com.startzhao.category.service;

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
public interface CategoryService {

    /**
     * 通过分类名得到分类数据
     * @param categoryName
     * @return
     */
    R getByName(String categoryName);
}
