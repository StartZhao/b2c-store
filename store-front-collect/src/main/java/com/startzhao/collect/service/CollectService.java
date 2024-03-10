package com.startzhao.collect.service;

import com.startzhao.param.CollectSaveParam;
import com.startzhao.utils.R;

/**
 * ClassName: CollectService
 * Package: com.startzhao.collect.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/10 10:25
 * @Version 1.0
 */
public interface CollectService {

    /**
     * 添加收藏
     * @param collectSaveParam
     * @return
     */
    R save(CollectSaveParam collectSaveParam);

    /**
     * 通过用户 id 查看收藏
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 删除收藏
     * @param collectSaveParam
     * @return
     */
    R remove(CollectSaveParam collectSaveParam);
}
