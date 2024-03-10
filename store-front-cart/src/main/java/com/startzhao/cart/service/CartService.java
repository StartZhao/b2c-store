package com.startzhao.cart.service;

import com.startzhao.param.CartUpdateParam;
import com.startzhao.param.CollectSaveParam;
import com.startzhao.utils.R;

/**
 * ClassName: CartService
 * Package: com.startzhao.cart.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/10 19:39
 * @Version 1.0
 */
public interface CartService {


    /**
     * 保存购物车信息
     * @param collectSaveParam
     * @return
     */
    R save(CollectSaveParam collectSaveParam);


    /**
     * 展示购物车信息
     * @param userId
     */
    R list(Integer userId);

    /**
     * 更新购物车信息
     * @param cartUpdateParam
     * @return
     */
    R update(CartUpdateParam cartUpdateParam);

    /**
     * 删除购物车信息
     * @param collectSaveParam
     * @return
     */
    R remove(CollectSaveParam collectSaveParam);
}
