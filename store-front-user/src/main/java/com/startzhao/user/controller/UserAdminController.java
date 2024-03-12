package com.startzhao.user.controller;

import com.startzhao.param.PageParam;
import com.startzhao.pojo.User;
import com.startzhao.user.service.UserService;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserAdminController
 * Package: com.startzhao.user.controller
 * Description: 后台调用用户相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/12 16:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @PostMapping("/admin/list")
    public R listPage(@RequestBody PageParam pageParam) {
        return userService.listPage(pageParam);
    }


    @PostMapping("/admin/remove")
    public R remove(@RequestBody Integer userId) {
        return userService.remove(userId);
    }


    @PostMapping("/admin/update")
    public R update(@RequestBody User user){
        return userService.update(user);
    }

    @PostMapping("/admin/save")
    public R save(@RequestBody User user){
        return userService.save(user);
    }


}
