package com.startzhao.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.startzhao.cart.mapper.CartMapper;
import com.startzhao.cart.service.CartService;
import com.startzhao.clients.ProductClient;
import com.startzhao.param.CartUpdateParam;
import com.startzhao.param.CollectSaveParam;
import com.startzhao.pojo.Cart;
import com.startzhao.pojo.Product;
import com.startzhao.utils.R;
import com.startzhao.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: CartServiceImpl
 * Package: com.startzhao.cart.service.impl
 * Description: 购物车业务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/10 19:39
 * @Version 1.0
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    
    @Autowired
    private ProductClient productClient;

    /**
     * 保存购物车信息，商品的数量不是一成不变的，且购物车的商品有可能被下架了
     * 1、判断购物车中商品是否还存在，决定能否添加到购物车中
     * 2、判断商品是否还有剩余库存
     * 3、判断是否已将该上商品加入购物车
     * @param collectSaveParam
     * @return
     */
    @Override
    public R save(CollectSaveParam collectSaveParam) {
        Integer userId = collectSaveParam.getUserId();
        Integer productId = collectSaveParam.getProductId();
        List<Integer> productIds = new ArrayList<>();
        productIds.add(productId);
        List<Product> productList = productClient.listByProductIds(productIds);
        if (productList.isEmpty()) {
            log.info("CartServiceImpl.save业务结束，结果{}", "商品已下架，请重新选择其他商品加入购物车");
            return R.fail("商品已下架，请重新选择其他商品加入购物车");
        }
        Product product = productList.get(0);
        int maxNum = product.getProductNum();
        if (maxNum == 0) {
            log.info("CartServiceImpl.save业务结束，结果{}", "商品已经没有库存，无法加入购物车！");
            R fail = R.fail("商品已经没有库存，无法加入购物车！");
            fail.setCode("003");
            return fail;
        }

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("product_id",productId);
        Cart cart = cartMapper.selectOne(queryWrapper);
        if (cart != null) {
            if (maxNum > cart.getNum()) {

                cart.setNum(cart.getId() + 1);
                cartMapper.update(cart,queryWrapper);
                log.info("CartServiceImpl.save业务结束，结果{}", "该商品已在购物车，数量加一");
                R fail = R.fail("该商品已在购物车，数量加一");
                fail.setCode("002");
                return fail;
            }
            log.info("CartServiceImpl.save业务结束，结果{}", "该商品数量已空");
            R fail = R.fail("该商品数量已空");
            fail.setCode("003");
            return fail;
        }
        cart = new Cart();
        cart.setProductId(productId);
        cart.setUserId(userId);
        cart.setNum(1);
        cartMapper.insert(cart);

        CartVO cartVO = new CartVO(product,cart);
        R ok = R.ok("添加购物车成功", cartVO);


        log.info("CartServiceImpl.save业务结束，结果{}", ok);



        return ok;
    }

    /**
     * 展示购物车信息
     *
     * @param userId
     */
    @Override
    public R list(Integer userId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        // 获得购物车信息
        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        // 购物车信息为空返回空数组
        List<CartVO> cartVOList = new ArrayList<>();
        if (cartList.isEmpty()) {
            log.info("CartServiceImpl.list业务结束，结果{}", "购物车信息为空");
            return R.ok("购物车信息为空",cartVOList);
        }
        //获得商品信息
        queryWrapper.select("product_id");
        List<Object> objects = cartMapper.selectObjs(queryWrapper);
        List<Integer> productIds = new ArrayList<>();
        objects.forEach(o -> {
            productIds.add((Integer) o);
        });
        List<Product> productList = productClient.listByProductIds(productIds);
        // 通过 map 来进行拼接
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));
        cartList.forEach(cart -> {
            CartVO cartVO = new CartVO(productMap.get(cart.getProductId()), cart);
            cartVOList.add(cartVO);
        });
        log.info("CartServiceImpl.list业务结束，结果{}", "购物车展示成功");


        return R.ok("购物车数据展示成功", cartVOList);
    }

    /**
     * 更新购物车信息
     *
     *
     * @param cartUpdateParam
     * @return
     */
    @Override
    public R update(CartUpdateParam cartUpdateParam) {
        Integer userId = cartUpdateParam.getUserId();
        Integer productId = cartUpdateParam.getProductId();
        Integer num = cartUpdateParam.getNum();
        List<Integer> productIds = new ArrayList<>();
        productIds.add(productId);
        List<Product> productList = productClient.listByProductIds(productIds);
        Product product = productList.get(0);
        // 得到商品库存信息
        int maxNum = product.getProductNum();
        if (num > maxNum) {
            log.info("CartServiceImpl.update业务结束，结果{}", "更新购物车失败，商品库存不足");
            return R.fail("更新购物车失败，商品库存不足");
        }
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("product_id",productId);
        Cart cart = cartMapper.selectOne(queryWrapper);
        cart.setNum(num);
        int rows = cartMapper.update(cart, queryWrapper);
        if (rows == 0) {
            log.info("CartServiceImpl.update业务结束，结果{}", "更新购物车失败，请稍后再试");
            return R.fail("更新购物车失败，请稍后再试");
        }
        log.info("CartServiceImpl.update业务结束，结果{}", "更新购物车成功");
        return R.ok("更新购物车成功");


    }

    /**
     * 删除购物车信息
     *
     * @param collectSaveParam
     * @return
     */
    @Override
    public R remove(CollectSaveParam collectSaveParam) {
        Integer userId = collectSaveParam.getUserId();
        Integer productId = collectSaveParam.getProductId();
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).eq("product_id",productId);
        int rows = cartMapper.delete(queryWrapper);
        if (rows == 0) {
            log.info("CartServiceImpl.update业务结束，结果{}", "删除购物车失败，请稍后再试");
            return R.fail("删除购物车失败，请稍后再试");
        }
        log.info("CartServiceImpl.update业务结束，结果{}", "删除购物车成功");
        return R.ok("删除购物车成功");
    }

    /**
     * 根据购物车 id 删除购物车信息
     *
     * @param cartIds
     */
    @Override
    @Transactional
    public void clearCart(List<Integer> cartIds) {
        cartMapper.deleteBatchIds(cartIds);
    }

    /**
     * 购物车是否引用商品
     *
     * @param productId
     * @return
     */
    @Override
    public Boolean reference(Integer productId) {
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        Long count = cartMapper.selectCount(queryWrapper);
        if (count == 0) {
            return false;
        }
        return true;
    }
}
