package com.startzhao.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startzhao.pojo.Orders;
import com.startzhao.vo.AdminOrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

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



    List<AdminOrderVO> adminList(Integer from, Integer size);


    Long adminTotal();
}
