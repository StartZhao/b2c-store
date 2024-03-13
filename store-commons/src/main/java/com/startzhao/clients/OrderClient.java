package com.startzhao.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: OrderClient
 * Package: com.startzhao.clients
 * Description: 订单客户端
 *
 * @Author StartZhao
 * @Create 2024/3/13 11:04
 * @Version 1.0
 */
@FeignClient("order-service")
public interface OrderClient {


    @PostMapping("/order/product/reference")
    Boolean reference(@RequestBody Integer productId);
}
