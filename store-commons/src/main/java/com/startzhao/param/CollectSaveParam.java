package com.startzhao.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassName: CollectSaveParam
 * Package: com.startzhao.param
 * Description: 添加收藏接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/10 10:31
 * @Version 1.0
 */
@Data
public class CollectSaveParam {


    @JsonProperty("product_id")
    @NotNull
    private Integer productId;
    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

}
