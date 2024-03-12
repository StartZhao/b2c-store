package com.startzhao.admin.service;

import com.startzhao.param.PageParam;
import com.startzhao.pojo.Category;
import com.startzhao.utils.R;

/**
 * ClassName: AdminCategoryService
 * Package: com.startzhao.admin.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/12 16:57
 * @Version 1.0
 */
public interface AdminCategoryService {

    /**
     * 类别展示
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);

    /**
     * 类别添加
     * @param categoryName
     * @return
     */
    R save(String categoryName);

    /**
     * 类别删除
     * @param categoryId
     * @return
     */
    R remove(Integer categoryId);

    /**
     * 更新类别数据
     * @param category
     * @return
     */
    R update(Category category);
}
