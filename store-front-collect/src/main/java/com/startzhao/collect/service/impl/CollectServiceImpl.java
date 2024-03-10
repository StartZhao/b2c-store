package com.startzhao.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.startzhao.clients.ProductClient;
import com.startzhao.collect.mapper.CollectMapper;
import com.startzhao.collect.service.CollectService;
import com.startzhao.param.CollectSaveParam;
import com.startzhao.pojo.Collect;
import com.startzhao.pojo.Product;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: CollectServiceImpl
 * Package: com.startzhao.collect.service.impl
 * Description: 收藏业务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/10 10:25
 * @Version 1.0
 */
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * 添加收藏
     * 1、判断是否存在该收藏
     * 2、 存在， 则返回已收藏
     * 3、 不存在，则添加收藏
     *
     * @param collectSaveParam
     * @return
     */
    @Override
    public R save(CollectSaveParam collectSaveParam) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        Integer productId = collectSaveParam.getProductId();
        Integer userId = collectSaveParam.getUserId();
        queryWrapper.eq("product_id", productId).eq("user_id", userId);
        Long rows = collectMapper.selectCount(queryWrapper);

        if (rows > 0) {
            log.info("CollectServiceImpl.save业务结束，结果{}", "该商品已经添加收藏，请到我的收藏查看");
            return R.fail("该商品已经添加收藏，请到我的收藏查看");
        }

        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setProductId(productId);
        collect.setCollectTime(System.currentTimeMillis());
        collectMapper.insert(collect);
        log.info("CollectServiceImpl.save业务结束，结果{}", "添加收藏成功");
        return R.ok("添加收藏成功");

    }

    /**
     * 通过用户 id 查看收藏
     * 1、通过用户 id 得到收藏的商品 id
     * 2、调用商品客户端得到 商品数据
     * 3、返回结果
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
//        List<Collect> collectList = collectMapper.selectList(queryWrapper);
//        if (collectList.isEmpty()) {
//            log.info("CollectServiceImpl.list业务结束，结果{}", "该用户，没有收藏");
//            return R.ok(null);
//        }
//        List<Integer> productIds = new ArrayList<>();
//        collectList.forEach((collect -> {
//            productIds.add(collect.getProductId());
//        })) ;
        queryWrapper.select("product_id");
        List<Object> productIds = collectMapper.selectObjs(queryWrapper);
        List<Integer> ids = new ArrayList<>();
        productIds.forEach(o -> {
            ids.add((Integer) o);
        });

//        ProductIdsParam productIdsParam = new ProductIdsParam();
//        productIdsParam.setProductIds(ids);
        if (ids.isEmpty()) {
            log.info("CollectServiceImpl.list业务结束，结果{}", "该用户不存在收藏");
            return R.ok(null);
        }

        List<Product> productList = productClient.listByProductIds(ids);
        R ok = R.ok(productList);
        log.info("CollectServiceImpl.list业务结束，结果{}", ok);

        return ok;
    }

    /**
     * 删除收藏
     * 1、得到收藏数
     * 2、若大于 0 删除收藏
     * 3、 若等于 0 删除失败
     *
     * @param collectSaveParam
     * @return
     */
    @Override
    public R remove(CollectSaveParam collectSaveParam) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        Integer productId = collectSaveParam.getProductId();
        Integer userId = collectSaveParam.getUserId();
        queryWrapper.eq("product_id", productId).eq("user_id", userId);
        Long rows = collectMapper.selectCount(queryWrapper);

        if (rows == 0) {
            log.info("CollectServiceImpl.save业务结束，结果{}", "没有该收藏，删除收藏失败");
            return R.fail("没有该收藏，删除收藏失败");
        }

        int rowsDelete = collectMapper.delete(queryWrapper);
        if (rowsDelete == 0) {
            log.info("CollectServiceImpl.save业务结束，结果{}", "删除收藏失败，请稍后再试！");
            return R.fail("删除收藏失败，请稍后再试！");
        }
        log.info("CollectServiceImpl.remove业务结束，结果{}", "删除收藏成功");
        return R.ok("删除收藏成功");
    }
}
