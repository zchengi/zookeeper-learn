package com.cheng.order.service.impl;

import com.cheng.order.mapper.OrdersMapper;
import com.cheng.order.pojo.Orders;
import com.cheng.order.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author cheng
 *         2018/10/3 13:39
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {

    private final static Logger log = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Orders getOrder(String orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public boolean createOrder(String itemId) {

        // 创建订单
        String oid = UUID.randomUUID().toString().replaceAll("-", "");
        Orders o = new Orders();
        o.setId(oid);
        o.setOrderNum(oid);
        o.setItemId(itemId);
        ordersMapper.insert(o);

        log.info("订单创建成功");

        return true;
    }
}

