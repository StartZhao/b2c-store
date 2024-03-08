package com.startzhao.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: ProductPromo
 * Package: com.startzhao.param
 * Description: 首页类别接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:51
 * @Version 1.0
 */
@Data
public class ProductPromo {

    @NotBlank
    private String categoryName;
}
