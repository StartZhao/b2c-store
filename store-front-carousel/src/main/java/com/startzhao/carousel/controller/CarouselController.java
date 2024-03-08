package com.startzhao.carousel.controller;

import com.startzhao.carousel.service.CarouselService;
import com.startzhao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: CarouselController
 * Package: com.startzhao.carousel.controller
 * Description: 轮播图相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/8 16:57
 * @Version 1.0
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @PostMapping("/list")
    public R list() {
        return carouselService.list();
    }
}
