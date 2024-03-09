package com.startzhao.clients;

import com.startzhao.param.ProductSearchParam;
import com.startzhao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName: SearchClient
 * Package: com.startzhao.clients
 * Description: 搜索客户端
 *
 * @Author StartZhao
 * @Create 2024/3/9 15:47
 * @Version 1.0
 */
@FeignClient("search-service")
public interface SearchClient {

    @PostMapping("/search/product")
    R product(@RequestBody ProductSearchParam productSearchParam);
}
