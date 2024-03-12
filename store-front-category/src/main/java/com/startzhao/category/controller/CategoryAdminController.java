package com.startzhao.category.controller;

import com.startzhao.category.service.CategoryService;
import com.startzhao.param.PageParam;
import com.startzhao.pojo.Category;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CategoryAdminController
 * Package: com.startzhao.category.controller
 * Description: 后台调用类别接口
 *
 * @Author StartZhao
 * @Create 2024/3/12 17:02
 * @Version 1.0
 */
@RestController
@RequestMapping("/category")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/admin/list")
    public R list(@RequestBody PageParam pageParam){
        return categoryService.list(pageParam);
    }
    @PostMapping("/admin/save")
    public R save(@RequestBody String categoryName){
        return categoryService.save(categoryName);
    }

    @PostMapping("/admin/remove")
    public R remove(@RequestBody Integer categoryId){
        return categoryService.remove(categoryId);
    }

    @PostMapping("/admin/update")
    public R update(@RequestBody Category category){
        return categoryService.update(category);
    }


}
