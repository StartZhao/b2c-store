package com.startzhao.admin.controller;

import com.startzhao.admin.commons.param.AdminUserParam;
import com.startzhao.admin.commons.pojo.AdminUser;
import com.startzhao.admin.service.AdminUserService;
import com.startzhao.param.PageParam;
import com.startzhao.pojo.User;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * ClassName: AdminUserController
 * Package: com.startzhao.admin.controller
 * Description: 管理用户controller
 *
 * @Author StartZhao
 * @Create 2024/3/11 23:50
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class AdminUserController {


    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("/login")
    public R login(AdminUserParam adminUserParam, BindingResult result, HttpSession session) {
        if (result.hasErrors()) return R.fail("核心参数为空，登录失败");
        if (!session.getAttribute("captcha").equals(adminUserParam.getVerCode())) return R.fail("验证输入错误，登陆失败！");
        AdminUser adminUser = adminUserService.login(adminUserParam);
        if (adminUser == null) return R.fail("登录失败，用户或密码错误");
        session.setAttribute("userInfo",adminUser);
        return R.ok("登录成功");
    }

    @GetMapping("/logout")
    public R logout(HttpSession session) {
        session.invalidate();
        return R.ok("退出登录成功");
    }


    @GetMapping("/list")
    public R list(PageParam pageParam) {
        return adminUserService.list(pageParam);
    }

    @PostMapping("/remove")
    public R remove(Integer userId) {
        return adminUserService.remove(userId);
    }

    @PostMapping("/update")
    public R update(User user) {
        return adminUserService.update(user);
    }



}
