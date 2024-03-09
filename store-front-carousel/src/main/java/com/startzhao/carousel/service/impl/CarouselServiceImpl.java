package com.startzhao.carousel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.startzhao.carousel.mapper.CarouselMapper;
import com.startzhao.carousel.service.CarouselService;
import com.startzhao.pojo.Carousel;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: CarouselServiceImpl
 * Package: com.startzhao.carousel.service.impl
 * Description: 轮播图业务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:00
 * @Version 1.0
 */
@Service
@Slf4j
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * 展示轮播图
     * 1、查询获得所有轮播图
     * 2、按照优先级获得 6 个轮播图对象
     * 3、返回查询结果
     * @return
     */
    @Override
    @Cacheable(value = "list.carousel", key = "#root.methodName")
    public R list() {
        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("priority");
        List<Carousel> list = carouselMapper.selectList(queryWrapper);
        List<Carousel> carouselList = list.stream().limit(6).collect(Collectors.toList());

        R ok = R.ok(carouselList);
        log.info("CarouselServiceImpl.list业务结束，结果{}", ok);
        return ok;
    }
}
