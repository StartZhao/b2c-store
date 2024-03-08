package com.startzhao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * ClassName: Carousel
 * Package: com.startzhao.pojo
 * Description: 轮播图实体类
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:03
 * @Version 1.0
 */
@Data
@TableName("carousel")
public class Carousel implements Serializable {

    public static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @TableField("carousel_id")
    @JsonProperty("carousel_id")
    private Integer carouselId;

    @TableField("img_path")
    private String imgPath;
    private String describes;
    @TableField("product_id")
    @JsonProperty("product_id")
    private Integer productId;
    private Integer priority;


}
