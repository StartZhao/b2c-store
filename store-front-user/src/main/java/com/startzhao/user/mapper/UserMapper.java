package com.startzhao.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startzhao.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: UserMapper
 * Package: com.startzhao.user.mapper
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/7 20:17
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
