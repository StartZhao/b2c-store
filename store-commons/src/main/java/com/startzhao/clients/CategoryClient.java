package com.startzhao.clients;

import com.startzhao.param.ProductHotsParam;
import com.startzhao.pojo.Category;
import com.startzhao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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

    @PostMapping("/category/hots")
    R hots(@RequestBody ProductHotsParam productHotsParam);

    @PostMapping("/category/list")
    R list();
}
