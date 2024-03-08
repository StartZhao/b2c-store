package com.startzhao.product.controller;

import com.startzhao.param.ProductHotsParam;
import com.startzhao.param.ProductPromo;
import com.startzhao.product.service.ProductService;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Binding;

/**
 * ClassName: ProductController
 * Package: com.startzhao.product.controller
 * Description: 商品服务相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/promo")
    public R promo(@RequestBody @Validated ProductPromo productPromo, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数异常，首页类别无法展示");
        return productService.promo(productPromo.getCategoryName());
    }

    @PostMapping("/hots")
    public R hots(@RequestBody @Validated ProductHotsParam productHotsParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，查询失败！");
        return productService.hots(productHotsParam);
    }

}
