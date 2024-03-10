package com.startzhao.cart.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startzhao.pojo.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

/**
 * ClassName: CartMapper
 * Package: com.startzhao.cart.mapper
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/10 19:40
 * @Version 1.0
 */
@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}
