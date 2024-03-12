package com.startzhao.admin.controller;

import com.startzhao.admin.commons.utils.AliyunOSSUtils;
import com.startzhao.admin.service.AdminProductService;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: AdminProductController
 * Package: com.startzhao.admin.controller
 * Description: 后台商品管理相关接口
 *
 * @Author StartZhao
 * @Create 2024/3/12 23:02
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;
    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @GetMapping("/list")
    public R list(ProductSearchParam productSearchParam) {
        return adminProductService.list(productSearchParam);
    }

    @PostMapping("/upload")
    public R upload(MultipartFile img) throws Exception {
        String filename = img.getOriginalFilename();
        filename = UUID.randomUUID().toString().replaceAll("-","") + filename;
        String contentType = img.getContentType();
        byte[] content = img.getBytes();
        int hours = 1000;
        String url = aliyunOSSUtils.uploadImage(filename, content, contentType, hours);
        System.out.println(url);
        log.info("AdminProductController.upload业务结束，结果{}", "图片上传成功!");
        return R.ok("图片上传成功！", url);
    }
}
