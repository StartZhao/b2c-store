package com.startzhao.admin.service.impl;

import com.startzhao.admin.service.AdminCategoryService;
import com.startzhao.clients.CategoryClient;
import com.startzhao.param.PageParam;
import com.startzhao.pojo.Category;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * ClassName: AdminCategotyServiceImpl
 * Package: com.startzhao.admin.service.impl
 * Description: 类别管理具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/12 16:58
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminCategotyServiceImpl implements AdminCategoryService {

    @Autowired
    private CategoryClient categoryClient;

    /**
     * 类别展示
     *
     * @param pageParam
     * @return
     */
    @Override
    @Cacheable(value = "list.category", key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    public R list(PageParam pageParam) {
        return categoryClient.list(pageParam);
    }

    /**
     * 类别添加
     *
     * @param categoryName
     * @return
     */
    @Override
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.category",allEntries = true)
            }
    )
    public R save(String categoryName) {
        return categoryClient.save(categoryName);
    }

    /**
     * 类别删除
     *
     * @param categoryId
     * @return
     */
    @Override@Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.category",allEntries = true),
                    @CacheEvict(value = "category",key = "#categoryId")
            }
    )
    public R remove(Integer categoryId) {
        return categoryClient.remove(categoryId);
    }

    /**
     * 更新类别数据
     *
     * @param category
     * @return
     */
    @Override
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.category",allEntries = true),
                    @CacheEvict(value = "category",key = "#category.categoryId")
            }
    )
    public R update(Category category) {
        return categoryClient.update(category);
    }
}
