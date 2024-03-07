package com.startzhao.user.service;

import com.startzhao.param.UserCheckParam;
import com.startzhao.param.UserLoginParam;
import com.startzhao.pojo.User;
import com.startzhao.utils.R;

/**
 * ClassName: UserService
 * Package: com.startzhao.user.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/7 20:12
 * @Version 1.0
 */
public interface UserService {

    /**
     * 检查账号是否可用业务
     * @param userCheckParam 账号参数已校验完成
     * @return 检查结果 001 004
     */
    R check(UserCheckParam userCheckParam);

    /**
     * 注册业务
     * @param user 参数已经校验，但是密码是明文！
     * @return 结果 001 004
     */
    R register(User user);

    /**
     * 登陆业务
     * @param userLoginParam 参数已经校验，但是密码是明文
     * @return 结果 001 data 或 004
     */
    R login(UserLoginParam userLoginParam);
}
