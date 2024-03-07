package com.startzhao.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: UserLoginParam
 * Package: com.startzhao.param
 * Description: 用户登录参数
 *
 * @Author StartZhao
 * @Create 2024/3/7 20:48
 * @Version 1.0
 */
@Data
public class UserLoginParam {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
