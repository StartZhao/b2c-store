package com.startzhao.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.startzhao.constants.UserConstants;
import com.startzhao.param.PageParam;
import com.startzhao.param.UserCheckParam;
import com.startzhao.param.UserLoginParam;
import com.startzhao.pojo.User;
import com.startzhao.user.mapper.UserMapper;
import com.startzhao.user.service.UserService;
import com.startzhao.utils.MD5Util;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Var;
import org.bouncycastle.est.CSRRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: UserServiceImpl
 * Package: com.startzhao.user.service.impl
 * Description: 用户业务
 *
 * @Author StartZhao
 * @Create 2024/3/7 20:14
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 检查账号是否可用业务
     *
     * @param userCheckParam 账号参数已校验完成
     * @return 检查结果 001 004
     */
    @Override
    public R check(UserCheckParam userCheckParam) {

        //参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userCheckParam.getUserName());

        //数据查询
        Long total = userMapper.selectCount(queryWrapper);

        //查询结果处理
        if (total == 0) {
            log.info("UserServiceImpl.check业务结束，结果{}", "账号可以使用！");
            return R.ok("账号不存在，用户可以使用！");
        }
        log.info("UserServiceImpl.check业务结束，结果{}", "账号不可使用！");
        return R.fail("账号已经存在，不可注册！");
    }

    /**
     * 注册业务
     * 1.检查用户是否存在
     * 2.进行MD5加密并进行加盐处理
     * 3.将用户存入数据库
     * 4.返回查询结果
     * @param user 参数已经校验，但是密码是明文！
     * @return 结果 001 004
     */
    @Override
    public R register(User user) {
        //参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());

        //数据查询
        Long total = userMapper.selectCount(queryWrapper);
        if (total > 0) {
            log.info("UserServiceImpl.register业务结束，结果{}", "用户已存在，注册失败！");
            return R.fail("用户已存在，注册失败！");
        }
        user.setPassword(MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT));
        int rows = userMapper.insert(user);
        if(rows == 0) {
            log.info("UserServiceImpl.register业务结束，结果{}", "数据插入失败！注册失败！");
            return R.fail("注册失败！请稍后再试！");
        }
        log.info("UserServiceImpl.register业务结束，结果{}", "注册成功");
        return R.ok("注册成功！");
    }

    /**
     * 登陆业务
     * 1，检查用户是否存在
     * 2. 对密码进行加密
     * 3. 查询比对
     * 4. 返回查询结果
     * @param userLoginParam 参数已经校验，但是密码是明文
     * @return 结果 001 data 或 004
     */
    @Override
    public R login(UserLoginParam userLoginParam) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userLoginParam.getUserName());
        Long total = userMapper.selectCount(queryWrapper);
        if (total == 0) {
            log.info("UserServiceImpl.login业务结束，结果{}", "用户不存在，登录失败");
            return R.fail("用户不存在，登录失败");
        }
        userLoginParam.setPassword(MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT));
        queryWrapper.eq("password", userLoginParam.getPassword());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("UserServiceImpl.login业务结束，结果{}", "密码错误，登录失败！");
            return R.fail("密码错误，登陆失败！");
        }
        log.info("UserServiceImpl.login业务结束，结果{}", "登录成功");
        user.setPassword(null);
        return R.ok("登录成功",user);
    }

    /**
     * 用户分页显示
     *
     * @param pageParam
     * @return
     */
    @Override
    public R listPage(PageParam pageParam) {
        IPage<User> page = new Page<>(pageParam.getCurrentPage(),pageParam.getPageSize());
        page = userMapper.selectPage(page,null);
        long total = page.getTotal();
        List<User> userList = page.getRecords();
        R ok = R.ok("用户查询成功", userList, total);
        log.info("UserServiceImpl.listPage业务结束，结果{}", ok);

        return ok;
    }

    /**
     * 用户数据删除
     *
     * @param userId
     * @return
     */
    @Override
    public R remove(Integer userId) {
        int rows = userMapper.deleteById(userId);
        if (rows == 0) {
            log.info("UserServiceImpl.remove业务结束，结果{}", "删除用户失败，请稍后再试");
            return R.fail("删除用户失败");
        }
        log.info("UserServiceImpl.remove业务结束，结果{}", "用户数据删除成功");
        return R.ok("用户数据删除成功");
    }

    /**
     * 更新用户数据
     *
     * @param user
     * @return
     */
    @Override
    public R update(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId()).eq("password",user.getPassword());
        Long count = userMapper.selectCount(queryWrapper);
        // 没查到数据，证明了密码被修改过
        if (count == 0) {
            String password = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
            user.setPassword(password);

        }

        int rows = userMapper.updateById(user);


        if (rows == 0) {
            log.info("UserServiceImpl.update业务结束，结果{}", "更新用户数据失败");
            return R.fail("更新用户数据失败");
        }
        log.info("UserServiceImpl.update业务结束，结果{}", "更新用户数据成功");
        return R.ok("更新用户数据成功");
    }

    /**
     * 添加用户
     * 密码需要加密处理
     * 需要判断用户是否存在
     * @param user
     * @return
     */
    @Override
    public R save(User user) {

        //参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());

        //数据查询
        Long total = userMapper.selectCount(queryWrapper);
        if (total > 0) {
            log.info("UserServiceImpl.register业务结束，结果{}", "用户已存在，添加失败！");
            return R.fail("用户已存在，添加失败！");
        }

        user.setPassword(MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT));
        int rows = userMapper.insert(user);
        if (rows == 0) {
            log.info("UserServiceImpl.save业务结束，结果{}", "新增用户失败");
            return R.ok("新增用户失败");
        }

        log.info("UserServiceImpl.save业务结束，结果{}", "保存成功");
        return R.ok("保存成功");
    }
}
