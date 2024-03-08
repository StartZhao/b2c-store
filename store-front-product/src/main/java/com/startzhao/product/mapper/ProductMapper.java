package com.startzhao.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startzhao.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName: ProductMapper
 * Package: com.startzhao.product.mappper
 * Description:
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:53
 * @Version 1.0
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
