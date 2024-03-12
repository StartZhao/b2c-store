package com.startzhao.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.startzhao.admin.commons.param.AdminUserParam;
import com.startzhao.admin.commons.pojo.AdminUser;
import com.startzhao.admin.mapper.AdminUserMapper;
import com.startzhao.admin.service.AdminUserService;
import com.startzhao.clients.UserClient;
import com.startzhao.constants.UserConstants;
import com.startzhao.param.PageParam;
import com.startzhao.pojo.User;
import com.startzhao.utils.MD5Util;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @Autowired
    private UserClient userClient;

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

    /**
     * 用户展示
     * 1、调用用户客户端得到分页查询
     * @return
     */
    @Override
    @Cacheable(value = "list.user", key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    public R list(PageParam pageParam) {
        return userClient.listPage(pageParam);

    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true),
                    @CacheEvict(value = "user", key = "#userId")
            }
    )
    public R remove(Integer userId) {
        return userClient.remove(userId);
    }

    /**
     * 更新用户数据
     * 由于展示用户就是直接拿数据库的密码信息，所以该密码已经被加密处理了
     * @param user
     * @return
     */
    @Override
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true),
                    @CacheEvict(value = "user",key = "#user.userId" )
            }
    )
    public R update(User user) {

        return userClient.update(user);
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Override
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true),
            }
    )
    public R save(User user) {
        return userClient.save(user);
    }
}
