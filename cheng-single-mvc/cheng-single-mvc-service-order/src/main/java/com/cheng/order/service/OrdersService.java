package com.cheng.order.service;

import com.cheng.pojo.Orders;

/**
 * @author cheng
 *         2018/10/3 13:38
 */
public interface OrdersService {

    /**
     * 根据订单id查询订单
     * @param orderId
     * @return
     */
    Orders getOrder(String orderId);
	
    /**
     * 下订单
     * @param itemId
     * @return
     */
    boolean createOrder(String itemId);
}

