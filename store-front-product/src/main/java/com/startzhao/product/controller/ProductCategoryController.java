package com.startzhao.product.controller;

import com.startzhao.product.service.ProductService;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ProductCategoryController
 * Package: com.startzhao.product.controller
 * Description: 类别调用商品接口
 *
 * @Author StartZhao
 * @Create 2024/3/12 19:51
 * @Version 1.0
 */
@RestController
@RequestMapping("/product/category")
public class ProductCategoryController {

    @Autowired
    private ProductService productService;

    @PostMapping("/exist")
    public boolean exist(Integer categoryId) {
        return productService.exist(categoryId);
    }
}
