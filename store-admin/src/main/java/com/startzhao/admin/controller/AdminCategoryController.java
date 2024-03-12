package com.startzhao.admin.controller;

import com.startzhao.admin.service.AdminCategoryService;
import com.startzhao.param.PageParam;
import com.startzhao.pojo.Category;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: AdminCategoryController
 * Package: com.startzhao.admin.controller
 * Description: 类别管理接口
 *
 * @Author StartZhao
 * @Create 2024/3/12 16:56
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class AdminCategoryController {

    @Autowired
    private AdminCategoryService adminCategoryService;

    @GetMapping("/list")
    public R list(PageParam pageParam) {
        return adminCategoryService.list(pageParam);
    }

    @PostMapping("/save")
    public R save(String categoryName) {
        return adminCategoryService.save(categoryName);
    }

    @PostMapping("/remove")
    public R remove(Integer categoryId) {
        return adminCategoryService.remove(categoryId);
    }

    @PostMapping("/update")
    public R remove(Category category) {
        return adminCategoryService.update(category);
    }
}
