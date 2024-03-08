package com.startzhao.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.startzhao.pojo.Address;
import com.startzhao.user.mapper.AddressMapper;
import com.startzhao.user.service.AddressService;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: AddressServiceImpl
 * Package: com.startzhao.user.service.impl
 * Description: 地址业务实现类
 *
 * @Author StartZhao
 * @Create 2024/3/8 12:43
 * @Version 1.0
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;
    /**
     * 根据用户 id 查询地址数据
     * 1、得到用户 id
     * 2、根据用户 id 查询地址信息
     * 3、返回查询结果
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);

        R ok = R.ok("地址查询成功", addressList);
        log.info("AddressServiceImpl.list业务结束，结果{}", ok);
        return ok;
    }

    /**
     * 添加地址信息
     * 1、插入地址到数据库
     * 2、返回最新地址信息
     * @param address 经过参数校验
     * @return
     */
    @Override
    public R save(Address address) {
        int rows = addressMapper.insert(address);
        if (rows == 0) {
            log.info("AddressServiceImpl.save业务结束，结果{}", "插入地址失败！");
            return R.fail("插入地址失败");
        }
        log.info("AddressServiceImpl.save业务结束，结果{}", "插入地址成功！");
        return list(address.getUserId());
    }

    /**
     * 根据 id 删除地址信息
     * 1、查询地址是否存在
     * 2、删除地址信息
     * 3、返回删除结果
     * @param id
     * @return
     */
    @Override
    public R delete(Integer id) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Address address = addressMapper.selectOne(queryWrapper);
        if (address == null) {
            log.info("AddressServiceImpl.delete业务结束，结果{}", "删除失败，地址不存在");
            return R.fail("删除失败，地址不存在");
        }
        int rows = addressMapper.deleteById(id);
        if(rows == 0) {
            log.info("AddressServiceImpl.delete业务结束，结果{}", "删除失败，请稍后再试");
            return R.fail("删除失败，请稍后再试");
        }
        log.info("AddressServiceImpl.delete业务结束，结果{}", "删除成功");
        return R.ok("删除成功");
    }
}
