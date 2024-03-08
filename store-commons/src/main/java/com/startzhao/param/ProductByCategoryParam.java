package com.startzhao.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * ClassName: ProductByCategoryParam
 * Package: com.startzhao.param
 * Description: 商品类别查询接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/8 23:11
 * @Version 1.0
 */
@Data
public class ProductByCategoryParam {

    @NotNull
    @JsonProperty("categoryID")
    private Integer[] categoryId;
    @NotNull
    private Integer currentPage = 1;    //默认值
    @NotNull
    private Integer pageSize = 15;  //默认值
}
