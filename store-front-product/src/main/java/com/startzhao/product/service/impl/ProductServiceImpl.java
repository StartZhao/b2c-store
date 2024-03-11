package com.startzhao.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startzhao.clients.CategoryClient;
import com.startzhao.clients.SearchClient;
import com.startzhao.param.ProductByCategoryParam;
import com.startzhao.param.ProductHotsParam;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.pojo.ProductPicture;
import com.startzhao.product.mapper.ProductMapper;
import com.startzhao.product.mapper.ProductPictureMapper;
import com.startzhao.product.service.ProductService;
import com.startzhao.to.OrderToProduct;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ClassName: ProductServiceImpl
 * Package: com.startzhao.product.service.impl
 * Description: 商品业务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/8 17:45
 * @Version 1.0
 */
@Slf4j
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPictureMapper productPictureMapper;


    /**
     * 根据首页类别显示商品 最多显示 7 件商品
     * 1、根据类别称，调用 feign 客户端访问分类服务获取分类数据
     * 2、根据类别查询商品 最多显示 7 件商品 销量降序排列
     * 2、返回查询结果
     *
     * @param categoryName
     * @return
     */
    @Override
    @Cacheable(value = "list.product", key = "#categoryName")
    public R promo(String categoryName) {
        R r = categoryClient.getByName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束，结果{}", "分类查询失败");
            return r;
        }
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();
        Integer categoryId = (Integer) map.get("category_id");

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId).orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1, 7);
        //返回的是包装数据! 内部有对应的商品集合,也有分页的参数 例如: 总条数 总页数等等
        page = productMapper.selectPage(page, queryWrapper);
        List<Product> productList = page.getRecords();
        if (productList.isEmpty()) {
            log.info("ProductServiceImpl.promo业务结束，结果{}", "该分类下没有商品");
            return R.fail("该分类下没有商品");
        }
        R ok = R.ok("数据查询成功", productList);
        log.info("ProductServiceImpl.promo业务结束，结果{}", ok);
        return ok;
    }

    /**
     * 多热门类别商品查询，最多查询 7 条商品
     * 1、根据类别称，调用 feign 客户端访问分类服务获取分类数据
     * 2、根据分类id，查询商品，根据销量降序
     * 3、返回查询结果
     *
     * @param productHotsParam
     * @return
     */
    @Override
    @Cacheable(value = "list.product", key = "#productHotsParam.categoryName")
    public R hots(ProductHotsParam productHotsParam) {
        R r = categoryClient.hots(productHotsParam);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.hots业务结束，结果{}", r.getMsg());
            return r;
        }
        List<Object> ids = (List<Object>) r.getData();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id", ids).orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);
        List<Product> productList = page.getRecords();
        if (productList.isEmpty()) {
            log.info("ProductServiceImpl.hots业务结束，结果{}", "该些分类下，不存在商品");
            return R.fail("该些分类下，不存在商品");
        }
        R ok = R.ok("多热门商品查询成功", productList);
        log.info("ProductServiceImpl.hots业务结束，结果{}", ok);
        return ok;
    }

    /**
     * 类别信息查询
     * 1、调用类别服务得到类别数据
     * 2、返回数据结果
     *
     * @return
     */
    @Override
    @Cacheable(value = "list.category", key = "#root.methodName", cacheManager = "cacheManagerDay")
    public R listCategory() {

        return categoryClient.list();
    }

    /**
     * 根据条件获取shangpin数据
     * 1、根据 条件查询商品
     * 2、返回查询结果
     *
     * @param productByCategoryParam
     * @return
     */
    @Override
    @Cacheable(value = "list.product", key = "#productByCategoryParam.categoryId+'-'+#productByCategoryParam.currentPage+'-'+#productByCategoryParam.pageSize")
    public R byCategory(ProductByCategoryParam productByCategoryParam) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (productByCategoryParam.getCategoryId() != null && productByCategoryParam.getCategoryId().length > 0) {
            queryWrapper.in("category_id", productByCategoryParam.getCategoryId());
        }

        IPage<Product> page = new Page<>(productByCategoryParam.getCurrentPage(), productByCategoryParam.getPageSize());
        page = productMapper.selectPage(page, queryWrapper);
        if (page.getTotal() == 0) {
            log.info("ProductServiceImpl.byCategory业务结束，结果{}", "不存在该条件商品数据，查询失败");
            return R.fail("不存在该条件商品数据，查询失败");
        }
        R ok = R.ok("查询成功", page.getRecords(), page.getTotal());
        log.info("ProductServiceImpl.byCategory业务结束，结果{}", ok);

        return ok;
    }

    /**
     * 根据商品 id 查询商品数据
     *
     * @param productId
     * @return
     */
    @Override
    @Cacheable(value = "product", key = "#productId")
    public R detail(Integer productId) {
//        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("product_id",productId);
//        Product product = productMapper.selectOne(queryWrapper);
        Product product = productMapper.selectById(productId);
        if (product == null) {
            log.info("ProductServiceImpl.detail业务结束，结果{}", "商品详情查询失败");
            return R.fail("商品详情查询失败");
        }
        log.info("ProductServiceImpl.detail业务结束，结果{}", "商品详情查询成功");
        return R.ok("商品详情查询成功", product);
    }

    /**
     * 根据商品 id 查询商品图像
     *
     * @param productId
     * @return
     */
    @Override
    @Cacheable(value = "picture", key = "#productId")
    public R pictures(Integer productId) {
        QueryWrapper<ProductPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        List<ProductPicture> productPictureList = productPictureMapper.selectList(queryWrapper);
        if (productPictureList == null) {
            log.info("ProductServiceImpl.detail业务结束，结果{}", "商品图片查询失败");
            return R.fail("商品图片查询失败");
        }
        log.info("ProductServiceImpl.detail业务结束，结果{}", "商品图片查询成功");
        return R.ok("商品详情查询成功", productPictureList);
    }

    /**
     * 查询所有商品
     *
     * @return 返回商品集合
     */
    @Override
    public List<Product> listProduct() {
        return productMapper.selectList(null);

    }

    /**
     * 商品搜索
     * 1、调用搜索客户端，得到商品数据
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {
        R ok = searchClient.product(productSearchParam);
        log.info("ProductServiceImpl.search业务结束，结果{}", ok);
        return ok;
    }

    /**
     * 根据商品 ids 得到商品数据
     *
     * @param ids
     * @return
     */
    @Override
    public List<Product> listProductByIds(List<Integer> ids) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", ids);

        return productMapper.selectList(queryWrapper);

    }

    /**
     * 修改库存，增加销量
     * 为了避免了进行频繁的sql操作，采用批量操作
     *
     * @param orderToProducts
     */
    @Override
    @Transactional
    public void subNumber(List<OrderToProduct> orderToProducts) {
//        orderToProducts.forEach(orderToProduct -> {
//            Integer productId = orderToProduct.getProductId();
//            Product product = productMapper.selectById(productId);
//            product.setProductNum(product.getProductNum() - orderToProduct.getNum());
//            product.setProductSales(product.getProductSales() + orderToProduct.getNum());
//            UpdateWrapper<Product> updateWrapper = new UpdateWrapper<>();
//            updateWrapper.eq("product_id", productId);
//            productMapper.update(product, updateWrapper);
//        });
        Map<Integer, OrderToProduct> orderToProductMap = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));
        Set<Integer> productIds = orderToProductMap.keySet();
        List<Product> productList = productMapper.selectBatchIds(productIds);
        productList.forEach(product -> {
            product.setProductNum(product.getProductNum() - orderToProductMap.get(product.getProductId()).getNum());
            product.setProductSales(product.getProductSales() + orderToProductMap.get(product.getProductId()).getNum());
        });

        updateBatchById(productList);

    }
}
