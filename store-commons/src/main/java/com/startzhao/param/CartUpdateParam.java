package com.startzhao.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassName: CartUpdateParam
 * Package: com.startzhao.param
 * Description: 购物车更新接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/10 22:43
 * @Version 1.0
 */
@Data
public class CartUpdateParam {

    @JsonProperty("product_id")
    @NotNull
    private Integer productId;
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

    @NotNull
    private Integer num;
}
