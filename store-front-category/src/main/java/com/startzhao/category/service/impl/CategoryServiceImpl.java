package com.startzhao.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.startzhao.category.mapper.CategoryMapper;
import com.startzhao.category.service.CategoryService;
import com.startzhao.clients.ProductClient;
import com.startzhao.param.PageParam;
import com.startzhao.pojo.Category;
import com.startzhao.utils.R;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ProductClient productClient;


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

    /**
     * 通过分类名得到分类数据，但分类名数量不确定需要使用模糊查询
     * 1、查询分类
     * 2、返回查询结果
     * @param categoryName
     * @return
     */
    @Override
    public R hots(List<String> categoryName) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name",categoryName).select("category_id");
        List<Object> ids = categoryMapper.selectObjs(queryWrapper);
        if (ids.isEmpty()) {
            log.info("CategoryServiceImpl.hots业务结束，结果{}", "不存在这些分类，查询失败");
            return R.fail("不存在这些分类，查询失败");
        }
        R ok = R.ok("查询分类成功", ids);
        log.info("CategoryServiceImpl.hots业务结束，结果{}", ok);
        return ok;
    }

    /**
     * 得到所有分类数据
     *
     * @return
     */
    @Override
    public R list() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        List<Category> categoryList = categoryMapper.selectList(queryWrapper);
        if (categoryList.isEmpty()) {
            log.info("CategoryServiceImpl.list业务结束，结果{}", "没有分类数据，查询失败");
            return R.fail("没有分类数据，查询失败");
        }
        R ok = R.ok("分类查询成功", categoryList);
        return ok;
    }

    /**
     * 分页类别展示
     *
     * @param pageParam
     * @return
     */
    @Override
    public R list(PageParam pageParam) {
        IPage<Category> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        page = categoryMapper.selectPage(page, null);
        log.info("CategoryServiceImpl.list业务结束，结果{}", "分页查询成功");
        return R.ok("查询成功",page.getRecords(),page.getSize());
    }

    /**
     * 类别数据添加
     *
     * @param categoryName
     * @return
     */
    @Override
    public R save(String categoryName) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",categoryName);
        Long count = categoryMapper.selectCount(queryWrapper);
        if (count > 0) {
            log.info("CategoryServiceImpl.save业务结束，结果{}", "类别已存在");
            R.fail("类别已存在");
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        int rows = categoryMapper.insert(category);
        if (rows == 0) {
            log.info("CategoryServiceImpl.save业务结束，结果{}", "类别添加失败");
            R.fail("类别添加失败");
        }
        log.info("CategoryServiceImpl.save业务结束，结果{}", "类别添加成功");
        return R.ok("类别添加成功");
    }


    /**
     * 类别数据删除
     * 该类别下有商品绑定不能删除
     * @param categoryId
     * @return
     */
    @Override
    public R remove(Integer categoryId) {
        // 判断类别是否被商品绑定， true 被绑定
        if (productClient.exist(categoryId)) {
            log.info("CategoryServiceImpl.remove业务结束，结果{}", "类别被商品绑定，无法删除");
            return R.fail("类别被商品绑定，无法删除");
        }

        int rows = categoryMapper.deleteById(categoryId);
        if (rows == 0) {
            log.info("CategoryServiceImpl.remove业务结束，结果{}", "删除类别失败");
            return R.fail("删除类别失败");
        }

        log.info("CategoryServiceImpl.remove业务结束，结果{}", "删除类别成功");
        return R.ok("删除类别成功");
    }

    /**
     * 类别数据更新
     * 若同名则无法更新
     * @param category
     * @return
     */
    @Override
    public R update(Category category) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);
        if (count != 0) {
            log.info("CategoryServiceImpl.update业务结束，结果{}", "已存在相同类别");
            return R.fail("已存在相同类别名");
        }
        int rows = categoryMapper.updateById(category);
        if (rows == 0) {
            log.info("CategoryServiceImpl.update业务结束，结果{}", "更新类别失败");
            return R.fail("更新类别失败");
        }

        log.info("CategoryServiceImpl.update业务结束，结果{}", "更新类别成功");
        return R.ok("更新类别成功");
    }
}
