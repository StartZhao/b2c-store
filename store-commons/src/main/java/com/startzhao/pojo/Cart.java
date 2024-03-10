package com.startzhao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Cart
 * Package: com.startzhao.pojo
 * Description: 购物车实体类
 *
 * @Author StartZhao
 * @Create 2024/3/10 19:41
 * @Version 1.0
 */
@Data
@TableName("cart")
public class Cart implements Serializable {

    public static final long serialVersionUID = 2L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer num;


}
