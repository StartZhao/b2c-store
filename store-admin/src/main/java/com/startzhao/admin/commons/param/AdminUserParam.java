package com.startzhao.admin.commons.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: AdminUserParam
 * Package: com.startzhao.admin.commons.param
 * Description: 管理用户接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/11 23:47
 * @Version 1.0
 */
@Data
public class AdminUserParam {

    @Length(min = 6)
    private String userAccount; //账号
    @Length(min = 6)
    private String userPassword; //密码
    @NotBlank
    private String verCode;  //验证码

}
