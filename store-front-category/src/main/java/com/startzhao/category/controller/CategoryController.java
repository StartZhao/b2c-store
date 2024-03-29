package com.startzhao.category.controller;

import com.startzhao.category.service.CategoryService;
import com.startzhao.param.ProductHotsParam;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: ProductController
 * Package: com.startzhao.product.controller
 * Description: 分类服务相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:43
 * @Version 1.0
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/promo/{categoryName}")
    public R getByName(@PathVariable String categoryName) {
        if (StringUtils.isEmpty(categoryName)){
            return R.fail("参数错误，无法查询分类数据");
        }
        return categoryService.getByName(categoryName);
    }

    @PostMapping("/hots")
    public R hots(@RequestBody @Validated ProductHotsParam productHotsParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，查询失败！");
        return categoryService.hots(productHotsParam.getCategoryName());
    }

    @PostMapping("/list")
    public R list(){
        return categoryService.list();
    }


}
