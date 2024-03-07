package com.startzhao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("user")
public class User implements Serializable {

    public static final long serialVersionUID = 1L;

    @JsonProperty("user_id")    // jackson 的注解，用于进行属性格式化
    @TableId(type = IdType.AUTO)
    private Integer userId;

    @Length(min = 6)
    private String userName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank
    private String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank
    private String userPhonenumber;



}
