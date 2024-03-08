package com.startzhao.user.service;

import com.startzhao.pojo.Address;
import com.startzhao.utils.R;

/**
 * ClassName: AddressService
 * Package: com.startzhao.user.service
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/8 12:41
 * @Version 1.0
 */
public interface AddressService {

    /**
     * 根据用户 id 查询地址数据
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 添加地址信息
     * @param address 经过参数校验
     * @return
     */
    R save(Address address);

    /**
     * 根据 id 删除地址信息
     * @param id
     * @return
     */
    R delete(Integer id);
}
