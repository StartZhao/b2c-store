package com.startzhao.search.controller;

import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.search.controller.service.SearchService;
import com.startzhao.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * ClassName: SearchController
 * Package: com.startzhao.search.controller
 * Description: 搜索服务相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/9 15:08
 * @Version 1.0
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/product")
    public R product(@RequestBody ProductSearchParam productSearchParam) {
        return searchService.product(productSearchParam);
    }


    @PostMapping("/save")
    public void save(@RequestBody Product product) throws IOException {
         searchService.save(product);
    }


    @PostMapping("/remove")
    public void save(@RequestBody Integer productId) throws IOException {
         searchService.remove(productId);
    }



}
