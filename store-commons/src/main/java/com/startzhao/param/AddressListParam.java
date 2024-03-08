package com.startzhao.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassName: AddressListParam
 * Package: com.startzhao.param
 * Description: 地址查看传入参数
 *
 * @Author StartZhao
 * @Create 2024/3/8 12:35
 * @Version 1.0
 */
@Data
public class AddressListParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
