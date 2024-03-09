package com.startzhao.search.controller.service;

import com.startzhao.param.ProductSearchParam;
import com.startzhao.utils.R;

/**
 * ClassName: SearchService
 * Package: com.startzhao.search.controller.service
 * Description: 搜索业务接口
 *
 * @Author StartZhao
 * @Create 2024/3/9 15:11
 * @Version 1.0
 */
public interface SearchService {

    /**
     * 商品搜索
     * @param productSearchParam
     * @return
     */
    R product(ProductSearchParam productSearchParam);
}
