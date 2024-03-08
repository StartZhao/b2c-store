package com.startzhao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ClassName: Address
 * Package: com.startzhao.pojo
 * Description: 地址实体类
 *
 * @Author StartZhao
 * @Create 2024/3/8 12:30
 * @Version 1.0
 */
@Data
@TableName("address")
public class Address implements Serializable {

    public static final long serialVersionUID = 2L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank
    private String linkman;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
    @NotNull
    @TableField("user_id")
    @JsonProperty("user_id")
    private Integer userId;
}
