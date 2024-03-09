package com.startzhao.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ClassName: PageParam
 * Package: com.startzhao.param
 * Description: 分页属性
 *
 * @Author StartZhao
 * @Create 2024/3/9 14:53
 * @Version 1.0
 */
@Data
public class PageParam {

    private Integer currentPage = 1;    //默认值
    private Integer pageSize = 15;  //默认值

    public Integer getFrom() {
        return (currentPage - 1) * pageSize;
    }

    public Integer getSize() {
        return pageSize;
    }
}
