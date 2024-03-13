package com.startzhao.order.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.startzhao.clients.ProductClient;
import com.startzhao.order.mapper.OrderMapper;
import com.startzhao.order.service.OrderService;
import com.startzhao.param.OrderParam;
import com.startzhao.pojo.Cart;
import com.startzhao.pojo.Orders;
import com.startzhao.pojo.Product;
import com.startzhao.to.OrderToProduct;
import com.startzhao.utils.R;
import com.startzhao.vo.CartVO;
import com.startzhao.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ClassName: OrderServiceImpl
 * Package: com.startzhao.service.impl
 * Description: 订单业务实现类
 * 因为需要对数据库进行批量操作故使用 MyBatis-Plus 对业务层的封装
 *
 * @Author StartZhao
 * @Create 2024/3/10 23:09
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ProductClient productClient;

    /**
     * 订单生成
     * TODO：支付功能待实现
     * 1、支付成功
     * 2、进行订单数据保存
     * 3、清空购物车数据，通过 cartId
     * 4、修改商品数据， 通过 productId、num 由于有多个属性 封装到 一个类中进行传递
     * 5、返回参数
     *
     * @param orderParam
     * @return
     */
    @Override
    @Transactional
    public R save(OrderParam orderParam) {
        Integer userId = orderParam.getUserId();
        Long orderId = System.currentTimeMillis();
        List<CartVO> products = orderParam.getProducts();
        List<Orders> ordersList = new ArrayList<>();
        //对 消息队列 需要发送的消息的数据进行组装
        List<Integer> cartIds = new ArrayList<>();
        List<OrderToProduct> orderToProducts = new ArrayList<>();
        products.forEach(cartVO -> {
            //对购物车消息数据进行填充
            cartIds.add(cartVO.getId());
            //对商品消息数据进行填充
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setProductId(cartVO.getProductID());
            orderToProduct.setNum(cartVO.getNum());
            orderToProducts.add(orderToProduct);

            Orders orders = new Orders();
            orders.setOrderId(orderId);
            orders.setUserId(userId);
            orders.setProductId(cartVO.getProductID());
            orders.setProductNum(cartVO.getNum());
            orders.setProductPrice(cartVO.getPrice());
            orders.setOrderTime(orderId);
            ordersList.add(orders);

        });
        saveBatch(ordersList);
        //向商品服务发送消息
        rabbitTemplate.convertAndSend("topic.ex", "sub.number", orderToProducts);
        //向购物车服务发送消息
        rabbitTemplate.convertAndSend("topic.ex", "clear.cart", cartIds);
        log.info("OrderServiceImpl.save业务结束，结果{}", "订单生成成功");
        return R.ok("订单生成成功");


    }


    /**
     * 订单展示业务
     * 分组查询订单数据
     * 1、查询用户对应的全部订单项
     * 2、利用 stream 进行订单分组 orderId
     * 3、查询订单的全部商品集合，并 stream 组成 map
     * 4、封装返回的 OrderVO 对象
     *
     * @param userId
     */
    @Override
    public R list(Integer userId) {
        // OrderVO 需要 商品和订单数据
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Orders> ordersList = list(queryWrapper);
        Map<Long, List<Orders>> orderMap = ordersList.stream().collect(Collectors.groupingBy(Orders::getOrderId));

        List<Integer> productIds = ordersList.stream().map(Orders::getProductId).collect(Collectors.toList());
        List<Product> productList = productClient.listByProductIds(productIds);
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));


        List<List<OrderVO>> result = new ArrayList<>();
        orderMap.values().forEach(ordersLists -> {
            List<OrderVO> orderVOList = new ArrayList<>();
            ordersLists.forEach(orders -> {
                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(orders, orderVO);
                orderVO.setProductName(productMap.get(orders.getProductId()).getProductName());
                orderVO.setProductPicture(productMap.get(orders.getProductId()).getProductPicture());
                orderVOList.add(orderVO);
            });
            result.add(orderVOList);
        });

        log.info("OrderServiceImpl.list业务结束，结果{}", "订单展示成功");

        return R.ok("订单展示成功", result);
    }

    /**
     * 订单是否引用商品
     *
     * @param productId
     * @return
     */
    @Override
    public Boolean reference(Integer productId) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        Long count = orderMapper.selectCount(queryWrapper);
        if (count == 0) {
            return false;
        }
        return true;
    }

}
