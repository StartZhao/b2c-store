package com.startzhao.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.startzhao.vo.CartVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: OrderParam
 * Package: com.startzhao.param
 * Description: 订单接收参数
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:12
 * @Version 1.0
 */
//订单参数
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderParam implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVO> products;

}


