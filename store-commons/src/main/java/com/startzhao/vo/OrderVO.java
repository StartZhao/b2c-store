package com.startzhao.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.startzhao.pojo.Orders;
import lombok.Data;

/**
 * ClassName: OrderVO
 * Package: com.startzhao.vo
 * Description: 订单回显
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:14
 * @Version 1.0
 */
//查询订单需要返回结果
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderVO extends Orders {

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;

}