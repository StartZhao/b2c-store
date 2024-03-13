package com.startzhao.search.controller.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.startzhao.clients.ProductClient;
import com.startzhao.doc.ProductDoc;
import com.startzhao.param.ProductSearchParam;
import com.startzhao.pojo.Product;
import com.startzhao.search.controller.service.SearchService;
import com.startzhao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SearchServiceImpl
 * Package: com.startzhao.search.controller.service.impl
 * Description: 搜索业务具体实现类
 *
 * @Author StartZhao
 * @Create 2024/3/9 15:12
 * @Version 1.0
 */
@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 商品搜索
     * 1、判断关键字是否为 null null 查询全部 不为 null all 字段查询
     * 2、添加分页属性
     * 3、es 查询
     * 4、结果处理
     * @param productSearchParam
     * @return
     */
    @Override
    public R product(ProductSearchParam productSearchParam) {

        SearchRequest searchRequest = new SearchRequest("product");
        String search = productSearchParam.getSearch();
        if (StringUtils.isEmpty(search)) {
            // 为空 查询所有数据
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        } else {
            // 不为空， 根据 all 字段查询
            searchRequest.source().query(QueryBuilders.matchQuery("all", search));
        }
        searchRequest.source().from(productSearchParam.getFrom());
        searchRequest.source().size(productSearchParam.getSize());
        SearchResponse searchResponse = null;
        try {
            searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查询错误");
        }
        // 商品服务 R msg total 符合的数量 | data 商品集合
        SearchHits hits = searchResponse.getHits();
        long total = hits.getTotalHits().value;
        SearchHit[] hitsHits = hits.getHits();
        List<Product> productList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (SearchHit hitsHit : hitsHits) {
            String sourceAsString = hitsHit.getSourceAsString();

            Product product = null;
            try {
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            productList.add(product);
        }
        R ok = R.ok("搜索成功", productList, total);
        log.info("SearchServiceImpl.product业务结束，结果{}", ok);
        return ok;
    }


    /**
     * 保存商品时更新商品es数据库
     *
     * @param product
     * @return
     */
    @Override
    public void save(Product product) throws IOException {
        IndexRequest indexRequest = new IndexRequest("product").id(product.getProductId().toString());
        ProductDoc productDoc = new ProductDoc(product);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(productDoc);
        indexRequest.source(json, XContentType.JSON);
        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除商品时更新商品es数据库
     *
     * @param productId
     */
    @Override
    public void remove(Integer productId) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest("product").id(productId.toString());
        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }
}
