package com.startzhao.product.controller;

import com.startzhao.pojo.Product;
import com.startzhao.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: ProductSearchController
 * Package: com.startzhao.product.controller
 * Description: 收藏调用商品服务
 *
 * @Author StartZhao
 * @Create 2024/3/9 10:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/product")
public class ProductCollectController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listIds")
    public List<Product> listByProductIds(@RequestParam List<Integer> ids) {
        return productService.listProductByIds(ids);
    }
}
