package com.startzhao.admin.service;

import com.startzhao.admin.commons.param.AdminUserParam;
import com.startzhao.admin.commons.pojo.AdminUser;

/**
 * ClassName: AdminUserService
 * Package: com.startzhao.admin.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/11 23:52
 * @Version 1.0
 */
public interface AdminUserService {

    /**
     * 用户登录
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);
}
