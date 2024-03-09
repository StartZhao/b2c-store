package com.startzhao.product.controller;

import com.startzhao.param.*;
import com.startzhao.product.service.ProductService;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数异常，首页类别无法展示");
        return productService.promo(productPromoParam.getCategoryName());
    }

    @PostMapping("/hots")
    public R hots(@RequestBody @Validated ProductHotsParam productHotsParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，查询失败！");
        return productService.hots(productHotsParam);
    }

    @PostMapping("/category/list")
    public R list() {
        return productService.list();
    }

    @PostMapping("/bycategory")
    public R byCategory(@RequestBody @Validated ProductByCategoryParam productByCategoryParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，查询失败");
        return productService.byCategory(productByCategoryParam);
    }

    @PostMapping("/all")
    public R all(@RequestBody @Validated ProductByCategoryParam productByCategoryParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，查询失败");
        return productService.byCategory(productByCategoryParam);
    }

    @PostMapping("/detail")
    public R detail(@RequestBody @Validated ProductDetailParam productDetailParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，查询详情失败");
        return productService.detail(productDetailParam.getProductId());
    }

    @PostMapping("/pictures")
    public R pictures(@RequestBody @Validated ProductDetailParam productDetailParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，查询详情失败");
        return productService.pictures(productDetailParam.getProductId());
    }

    @PostMapping("/search")
    public R search(@RequestBody ProductSearchParam productSearchParam) {
        return productService.search(productSearchParam);
    }

}
