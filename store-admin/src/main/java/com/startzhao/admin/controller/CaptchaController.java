package com.startzhao.admin.controller;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: CaptchaController
 * Package: com.startzhao.admin.controller
 * Description: 验证码controller
 *
 * @Author StartZhao
 * @Create 2024/3/11 23:41
 * @Version 1.0
 */
@RestController
public class CaptchaController {


    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 默认存放四位长度的验证码
        //默认存放到 session key = captcha
        CaptchaUtil.out(request,response);
    }


}
