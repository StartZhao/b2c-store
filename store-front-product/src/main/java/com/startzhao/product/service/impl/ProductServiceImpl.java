package com.startzhao.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.startzhao.clients.CategoryClient;
import com.startzhao.param.ProductHotsParam;
import com.startzhao.pojo.Product;
import com.startzhao.product.mapper.ProductMapper;
import com.startzhao.product.service.ProductService;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * ClassName: ProductServiceImpl
 * Package: com.startzhao.product.service.impl
 * Description: 商品业务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:45
 * @Version 1.0
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 根据首页类别显示商品 最多显示 7 件商品
     * 1、根据类别称，调用 feign 客户端访问分类服务获取分类数据
     * 2、根据类别查询商品 最多显示 7 件商品 销量降序排列
     * 2、返回查询结果
     * @param categoryName
     * @return
     */
    @Override
    public R promo(String categoryName) {
        R r = categoryClient.getByName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束，结果{}", "分类查询失败");
            return r;
        }
        LinkedHashMap<String,Object> map = (LinkedHashMap<String, Object>) r.getData();
        Integer categoryId = (Integer) map.get("categoryId");

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",categoryId).orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1, 7);
        //返回的是包装数据! 内部有对应的商品集合,也有分页的参数 例如: 总条数 总页数等等
        page = productMapper.selectPage(page, queryWrapper);
        List<Product> productList = page.getRecords();
        if(productList.isEmpty()) {
            log.info("ProductServiceImpl.promo业务结束，结果{}", "该分类下没有商品");
            return R.fail("该分类下没有商品");
        }
        R ok = R.ok("数据查询成功", productList);
        log.info("ProductServiceImpl.promo业务结束，结果{}", ok);
        return ok;
    }

    /**
     * 多热门类别商品查询，最多查询 7 条商品
     * 1、根据类别称，调用 feign 客户端访问分类服务获取分类数据
     * 2、根据分类id，查询商品，根据销量降序
     * 3、返回查询结果
     * @param productHotsParam
     * @return
     */
    @Override
    public R hots(ProductHotsParam productHotsParam) {
        R r = categoryClient.hots(productHotsParam);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.hots业务结束，结果{}", r.getMsg());
            return r;
        }
        List<Object> ids = (List<Object>) r.getData();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id",ids).orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);
        List<Product> productList = page.getRecords();
        if (productList.isEmpty()) {
            log.info("ProductServiceImpl.hots业务结束，结果{}", "该些分类下，不存在商品");
            return R.fail("该些分类下，不存在商品");
        }
        R ok = R.ok("多热门商品查询成功", productList);
        log.info("ProductServiceImpl.hots业务结束，结果{}", ok);
        return ok;
    }
}
