package com.startzhao.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: AdminOrderVO
 * Package: com.startzhao.vo
 * Description: 后台管理订单回显实体类
 *
 * @Author StartZhao
 * @Create 2024/3/13 21:31
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminOrderVO implements Serializable {

    public static final long serialVersionUID = 2L;

    @JsonProperty("order_id")
    private Long    orderId; //订单编号,选择使用时间戳
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_num")
    private Integer productNum; //订单种类
    @JsonProperty("order_num")
    private Integer orderNum; //订单商品件数
    @JsonProperty("order_price")
    private Integer orderPrice; //时间戳




}
