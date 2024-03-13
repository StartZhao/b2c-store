package com.startzhao.search.controller.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.utils.R;

import java.io.IOException;

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

    /**
     * 保存商品时更新商品es数据库
     * @param product
     * @return
     */
    void save(Product product) throws IOException;

    /**
     * 删除商品时更新商品es数据库
     * @param productId
     */
    void remove(Integer productId) throws IOException;
}
