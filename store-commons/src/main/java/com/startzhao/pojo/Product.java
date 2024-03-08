package com.startzhao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * ClassName: User
 * Package: com.startzhao.pojo
 * Description: 用户实体类
 *
 * @Author StartZhao
 * @Create 2024/3/7 19:49
 * @Version 1.0
 */
@Data
@TableName("product")
public class Product implements Serializable {

    public static final long serialVersionUID = 1L;

    @JsonProperty("product_id")    // jackson 的注解，用于进行属性格式化
    @TableId(type = IdType.AUTO)
    private Integer productId;

    @TableField("product_name")
    private String productName;
    @TableField("category_id")
    @JsonProperty("category_id")
    private Integer categoryId;
    @JsonProperty("product_title")
    private String productTitle;
    @JsonProperty("product_intro")
    private String productIntro;
    @JsonProperty("product_picture")
    private String productPicture;
    @JsonProperty("product_selling_price")
    private Double productSellingPrice;
    @JsonProperty("product_num")
    private Integer productNum;
    @JsonProperty("product_sales")
    private Integer productSales;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double productPrice;




}
