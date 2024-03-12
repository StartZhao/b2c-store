package com.startzhao.clients;

import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("/product/listIds")
    List<Product> listByProductIds(@RequestParam List<Integer> ids);

    @PostMapping("/product/category/exist")
    boolean exist(@RequestParam Integer categoryId);

    @PostMapping("/product/admin/list")
    R list(@RequestBody ProductSearchParam productSearchParam);
}
