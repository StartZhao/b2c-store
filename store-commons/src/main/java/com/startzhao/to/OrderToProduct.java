package com.startzhao.to;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: OrderToProduct
 * Package: com.startzhao.to
 * Description: 订单发送商品服务实体
 *
 * @Author StartZhao
 * @Create 2024/3/11 10:49
 * @Version 1.0
 */
@Data
public class OrderToProduct implements Serializable {

    public static final long serialVersionUID = 1L;

    private Integer productId;
    private Integer num;
}
