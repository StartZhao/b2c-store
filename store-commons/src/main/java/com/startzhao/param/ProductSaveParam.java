package com.startzhao.param;

import com.startzhao.pojo.Product;
import lombok.Data;

/**
 * ClassName: ProductSaveParam
 * Package: com.startzhao.param
 * Description: 商品保存接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/13 10:00
 * @Version 1.0
 */
@Data
public class ProductSaveParam extends Product {

    //商品详情地址，多图片地址使用 + 号拼接
    private String pictures;
}
