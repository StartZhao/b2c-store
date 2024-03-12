package com.startzhao.admin.service;

import com.startzhao.param.ProductSearchParam;
import com.startzhao.utils.R;

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

}
