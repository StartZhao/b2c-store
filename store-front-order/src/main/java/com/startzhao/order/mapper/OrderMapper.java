package com.startzhao.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startzhao.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: OrderMapper
 * Package: com.startzhao.mapper
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:10
 * @Version 1.0
 */
@Repository
public interface OrderMapper extends BaseMapper<Orders> {
}
