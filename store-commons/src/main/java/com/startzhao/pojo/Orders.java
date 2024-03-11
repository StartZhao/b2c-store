package com.startzhao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Orders
 * Package: com.startzhao.pojo
 * Description: 订单实体类
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:03
 * @Version 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@TableName("orders")
public class Orders implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @JsonProperty("order_id")
    private Long    orderId; //订单编号,选择使用时间戳
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("product_num")
    private Integer productNum;
    @JsonProperty("product_price")
    private Double  productPrice;
    @JsonProperty("order_time")
    private Long    orderTime;
}
