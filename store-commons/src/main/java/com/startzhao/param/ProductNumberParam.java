package com.startzhao.param;

import lombok.Data;

/**
 * ClassName: ProductNumberParam
 * Package: com.startzhao.param
 * Description: 商品库存信息
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:13
 * @Version 1.0
 */
//调用product参数



@Data
public class ProductNumberParam {

    //商品id
    private Integer productId;
    //购买数量
    private Integer productNum;
}
