package com.startzhao.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * ClassName: ProductHotsParam
 * Package: com.startzhao.param
 * Description: 首页热门查询接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/8 21:37
 * @Version 1.0
 */
@Data
public class ProductHotsParam {

    @NotEmpty
    private List<String> categoryName;
}
