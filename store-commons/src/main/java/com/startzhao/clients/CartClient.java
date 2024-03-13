package com.startzhao.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: CartClient
 * Package: com.startzhao.clients
 * Description: 购物车客户端
 *
 * @Author StartZhao
 * @Create 2024/3/13 11:00
 * @Version 1.0
 */
@FeignClient("cart-service")
public interface CartClient {


    @PostMapping("/cart/product/reference")
    Boolean reference(@RequestBody Integer productId);
}
