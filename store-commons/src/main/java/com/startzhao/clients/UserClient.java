package com.startzhao.clients;

import com.startzhao.param.PageParam;
import com.startzhao.pojo.User;
import com.startzhao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: UserClient
 * Package: com.startzhao.clients
 * Description: 用户客户端
 *
 * @Author StartZhao
 * @Create 2024/3/12 9:03
 * @Version 1.0
 */
@FeignClient(value = "user-service")
public interface UserClient {


    @PostMapping("/user/admin/list")
    R listPage(@RequestBody PageParam pageParam);


    @PostMapping("/user/admin/remove")
    R remove(@RequestBody Integer userId);

    @PostMapping("/user/admin/update")
    R update(@RequestBody User user);


    @PostMapping("/user/admin/save")
    R save(@RequestBody User user);

}
