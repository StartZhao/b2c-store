package com.startzhao.collect.controller;

import com.startzhao.collect.service.CollectService;
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
 * ClassName: CollectController
 * Package: com.startzhao.collect.controller
 * Description: 收藏服务相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/10 10:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;


    @PostMapping("/save")
    public R save(@RequestBody @Validated CollectSaveParam collectSaveParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，添加收藏失败");
        return collectService.save(collectSaveParam);
    }


    @PostMapping("/list")
    public R list(@RequestBody Map<String, Integer> parm) {
        return collectService.list(parm.get("user_id"));
    }

    @PostMapping("/remove")
    public R remove(@RequestBody @Validated CollectSaveParam collectSaveParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数错误，删除收藏失败");
        return collectService.remove(collectSaveParam);
    }

}
