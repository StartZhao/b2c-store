package com.startzhao.admin.commons.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: AdminUser
 * Package: com.startzhao.admin.commons.pojo
 * Description: 管理用户实体类
 *
 * @Author StartZhao
 * @Create 2024/3/11 23:49
 * @Version 1.0
 */
@Data
@TableName("admin_user")
public class AdminUser  implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String userPhone;
    private Date createTime;
    private Integer userRole;

}
