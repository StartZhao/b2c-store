package com.startzhao.clients;

import com.startzhao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName: CategoryClient
 * Package: com.startzhao.clients
 * Description: 分类调用接口
 *
 * @Author StartZhao
 * @Create 2024/3/8 20:51
 * @Version 1.0
 */
@FeignClient("category-service")
public interface CategoryClient {

    @GetMapping("/category/promo/{categoryName}")
    R getByName(@PathVariable String categoryName);
}
