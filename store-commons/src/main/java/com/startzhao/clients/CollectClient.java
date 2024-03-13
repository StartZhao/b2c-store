package com.startzhao.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: CollectClient
 * Package: com.startzhao.clients
 * Description: 收藏客户端
 *
 * @Author StartZhao
 * @Create 2024/3/13 11:03
 * @Version 1.0
 */
@FeignClient("collect-service")
public interface CollectClient {

    @PostMapping("/collect/product/reference")
    Boolean reference(@RequestBody Integer productId);
}
