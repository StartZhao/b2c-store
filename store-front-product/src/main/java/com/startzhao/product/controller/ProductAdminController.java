package com.startzhao.product.controller;

import com.startzhao.param.ProductSearchParam;
import com.startzhao.product.service.ProductService;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProductAdminController
 * Package: com.startzhao.product.controller
 * Description: 后台调用商品相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/12 23:11
 * @Version 1.0
 */
@RestController
@RequestMapping("/product/admin")
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @PostMapping("/list")
    public R list(@RequestBody ProductSearchParam productSearchParam) {
        return productService.search(productSearchParam);
    }

}
