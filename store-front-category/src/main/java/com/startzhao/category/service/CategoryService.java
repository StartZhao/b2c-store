package com.startzhao.category.service;

import com.startzhao.param.PageParam;
import com.startzhao.pojo.Category;
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
public interface CategoryService {

    /**
     * 通过分类名得到分类数据
     * @param categoryName
     * @return
     */
    R getByName(String categoryName);

    /**
     * 通过分类名得到分类数据，但分类名数量不确定需要使用模糊查询
     * @param categoryName
     * @return
     */
    R hots(List<String> categoryName);

    /**
     * 得到所有分类数据
     * @return
     */
    R list();

    /**
     * 分页类别展示
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);

    /**
     * 类别数据添加
     * @param categoryName
     * @return
     */
    R save(String categoryName);

    /**
     * 类别数据删除
     * @param categoryId
     * @return
     */
    R remove(Integer categoryId);

    /**
     * 类别数据更新
     * @param category
     * @return
     */
    R update(Category category);
}
