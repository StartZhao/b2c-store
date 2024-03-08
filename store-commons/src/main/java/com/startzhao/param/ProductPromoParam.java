package com.startzhao.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: ProductPromoParam
 * Package: com.startzhao.param
 * Description: 首页类别接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:51
 * @Version 1.0
 */
@Data
public class ProductPromoParam {

    @NotBlank
    private String categoryName;
}
