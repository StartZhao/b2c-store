package com.startzhao.user.controller;

import com.startzhao.param.PageParam;
import com.startzhao.param.UserCheckParam;
import com.startzhao.param.UserLoginParam;
import com.startzhao.pojo.User;
import com.startzhao.user.service.UserService;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: UserController
 * Package: com.startzhao.user.controller
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/7 19:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result) {

        //检查是否符合校验注解的规则， 符合 false， 不符合 true
        boolean errors = result.hasErrors();
        if (errors) return R.fail("账号为空，不可使用！");
        return userService.check(userCheckParam);
    }

    @PostMapping("/register")
    public R register(@RequestBody @Validated User user, BindingResult result) {
        if(result.hasErrors()) {
            return R.fail("参数异常，不可注册");
        }
        return userService.register(user);
    }

    @PostMapping("/login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数异常，登录失败");
        return userService.login(userLoginParam);
    }




}
