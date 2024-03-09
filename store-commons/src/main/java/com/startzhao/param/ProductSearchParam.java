package com.startzhao.param;

import lombok.Data;

/**
 * ClassName: ProductSearchParam
 * Package: com.startzhao.param
 * Description: 搜索关键字和分页参数集合
 *
 * @Author StartZhao
 * @Create 2024/3/9 14:52
 * @Version 1.0
 */
@Data
public class ProductSearchParam extends PageParam{

    private String search;

}
