package com.startzhao.clients;

import com.startzhao.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * ClassName: ProductClient
 * Package: com.startzhao.clients
 * Description: 商品服务调用客户端
 *
 * @Author StartZhao
 * @Create 2024/3/9 10:57
 * @Version 1.0
 */
@FeignClient(value = "product-service")
public interface ProductClient {

    @GetMapping("/product/list")
    List<Product> list();
}
