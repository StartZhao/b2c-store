package com.startzhao.user.controller;

import com.startzhao.param.AddressListParam;
import com.startzhao.param.AddressRemoveParam;
import com.startzhao.param.AddressSaveParam;
import com.startzhao.pojo.Address;
import com.startzhao.user.service.AddressService;
import com.startzhao.utils.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: AddressController
 * Package: com.startzhao.user.controller
 * Description: 地址接口
 *
 * @Author StartZhao
 * @Create 2024/3/8 12:37
 * @Version 1.0
 */
@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/list")
    public R list(@RequestBody @Validated AddressListParam addressListParam, BindingResult result) {
        if(result.hasErrors()) return R.fail("参数异常，查询失败");
        return addressService.list(addressListParam.getUserId());
    }
    @PostMapping("/save")
    public R save(@RequestBody @Validated AddressSaveParam addressSaveParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数异常，添加失败");
        Address address = new Address();
        BeanUtils.copyProperties(addressSaveParam.getAdd(),address);
        address.setUserId(addressSaveParam.getUserId());
        return addressService.save(address);
    }
    @PostMapping("/remove")
    public R delete(@RequestBody @Validated AddressRemoveParam addressRemoveParam, BindingResult result) {
        if (result.hasErrors()) return R.fail("参数异常，删除失败");
        return addressService.delete(addressRemoveParam.getId());
    }
}
