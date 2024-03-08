package com.startzhao.category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.startzhao.pojo.Category;
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
public interface CategoryMapper extends BaseMapper<Category> {
}
