package com.startzhao.admin.service;

import com.startzhao.admin.commons.param.AdminUserParam;
import com.startzhao.admin.commons.pojo.AdminUser;
import com.startzhao.param.PageParam;
import com.startzhao.pojo.User;
import com.startzhao.utils.R;

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

    /**
     * 用户展示
     * @return
     */
    R list(PageParam pageParam);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    R remove(Integer userId);

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    R update(User user);

    /**
     * 保存用户
     * @param user
     * @return
     */
    R save(User user);
}
