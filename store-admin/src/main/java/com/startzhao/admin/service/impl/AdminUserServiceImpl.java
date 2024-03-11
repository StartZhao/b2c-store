package com.startzhao.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.startzhao.admin.commons.param.AdminUserParam;
import com.startzhao.admin.commons.pojo.AdminUser;
import com.startzhao.admin.mapper.AdminUserMapper;
import com.startzhao.admin.service.AdminUserService;
import com.startzhao.constants.UserConstants;
import com.startzhao.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: AdminUserServiceImpl
 * Package: com.startzhao.admin.service.impl
 * Description: 管理用户服务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/11 23:52
 * @Version 1.0
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 用户登录
     *
     * @param adminUserParam
     * @return
     */
    @Override
    public AdminUser login(AdminUserParam adminUserParam) {
        String account = adminUserParam.getUserAccount();
        String password = MD5Util.encode(adminUserParam.getUserPassword() + UserConstants.USER_SLAT);
        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",account).eq("user_password",password);
        return adminUserMapper.selectOne(queryWrapper);
    }
}
