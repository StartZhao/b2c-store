package com.startzhao.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.startzhao.category.mapper.CategoryMapper;
import com.startzhao.category.service.CategoryService;
import com.startzhao.pojo.Category;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: ProductServiceImpl
 * Package: com.startzhao.product.service.impl
 * Description: 分类业务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:45
 * @Version 1.0
 */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 通过分类名得到分类数据
     * 1、查询分类数据
     * 2、返回分类信息
     * @param categoryName
     * @return
     */
    @Override
    public R getByName(String categoryName) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", categoryName);
        Category category = categoryMapper.selectOne(queryWrapper);
        if (category == null) {
            log.info("CategoryServiceImpl.getByName业务结束，结果{}", "不存在该分类，分类查询错误！");
            return R.fail("不存在该分类，分类查询错误");
        }
        R ok = R.ok("分类查询成功",category);
        log.info("CategoryServiceImpl.getByName业务结束，结果{}", ok);
        return ok;
    }
}