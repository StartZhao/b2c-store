package com.startzhao.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: UserCheckParam
 * Package: com.startzhao.param
 * Description: 用户检查传入参数
 *  使用 jsr 303的注解 进行参数校验
 * @Author StartZhao
 * @Create 2024/3/7 19:56
 * @Version 1.0
 */
@Data
public class UserCheckParam {

    @NotBlank
    private String userName;    //注意： 参数名称要等于前端传递的 JSON key 的名称！

}
