package com.startzhao.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.startzhao.pojo.Cart;
import com.startzhao.pojo.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ClassName: CartVO
 * Package: com.startzhao.vo
 * Description: 购物车回显实体类
 *
 * @Author StartZhao
 * @Create 2024/3/10 20:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartVO implements Serializable {

    public static final long serialVersionUID = 2L;

    private Integer id;  //购物车id
    private Integer productID;  //商品id
    private String  productName; //商品名称
    private String  productImg; //商品显示图片
    private Double price;  //商城价格
    private Integer num;  //商品购买数量
    private Integer maxNum; //商品限购数量
    private Boolean check = false; //是否勾选

    public CartVO(Product product, Cart cart) {
        this.id = cart.getId();
        this.productID = product.getProductId();
        this.productName = product.getProductName();
        this.productImg = product.getProductPicture();
        this.price = product.getProductSellingPrice();
        this.num = cart.getNum();
        this.maxNum = product.getProductNum();
        this.check = false;
    }


}
