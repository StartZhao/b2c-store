package com.startzhao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: ProductPicture
 * Package: com.startzhao.pojo
 * Description: 商品图像实体类
 *
 * @Author StartZhao
 * @Create 2024/3/8 23:53
 * @Version 1.0
 */
@Data
@TableName("product_picture")
public class ProductPicture implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("product_picture")
    private String  productPicture;
    private String  intro;
}
