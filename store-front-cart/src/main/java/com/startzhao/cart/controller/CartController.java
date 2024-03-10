package com.startzhao.cart.controller;

import com.startzhao.cart.service.CartService;
import com.startzhao.param.CartUpdateParam;
import com.startzhao.param.CollectSaveParam;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: CartController
 * Package: com.startzhao.cart.controller
 * Description: 购物车相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/10 19:38
 * @Version 1.0
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping("/save")
    public R save(@RequestBody @Validated CollectSaveParam collectSaveParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，购物车保存失败");
        return cartService.save(collectSaveParam);
    }

    @PostMapping("/list")
    public R list(@RequestBody Map<String, Integer> param) {
        return cartService.list(param.get("user_id"));
    }

    @PostMapping("/update")
    public R update(@RequestBody @Validated CartUpdateParam cartUpdateParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，购物车更新失败");
        return cartService.update(cartUpdateParam);
    }

    @PostMapping("/remove")
    public R remove(@RequestBody @Validated CollectSaveParam collectSaveParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，购物车删除失败");
        return cartService.remove(collectSaveParam);
    }




}
