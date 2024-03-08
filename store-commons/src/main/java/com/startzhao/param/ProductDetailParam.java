package com.startzhao.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassName: ProductDetailParam
 * Package: com.startzhao.param
 * Description: 商品详情接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/8 23:45
 * @Version 1.0
 */
@Data
public class ProductDetailParam {

    @JsonProperty("productID")
    @NotNull
    private Integer productId;
}
