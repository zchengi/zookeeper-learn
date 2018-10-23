package com.cheng.web.service.impl;


import com.cheng.item.service.ItemsService;
import com.cheng.order.service.OrdersService;
import com.cheng.web.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cheng
 *         2018/10/3 13:50
 */
@Service("buyService")
public class CustomerServiceImpl implements CustomerService {
	
	private final static Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private ItemsService itemsService;

	@Autowired
	private OrdersService ordersService;

	@Override
	public void doBuyItem(String itemId) {
		// 减少库存
		itemsService.displayReduceCounts(itemId, 1);
		
		// 创建订单
		ordersService.createOrder(itemId);
	}
	
	@Override
	public boolean displayBuy(String itemId) {

        int buyCounts = 6;
		
		// 1. 判断库存
		int stockCounts = itemsService.getItemCounts(itemId);
		if (stockCounts < buyCounts) {
			log.info("库存剩余{}件，用户需求量{}件，库存不足，订单创建失败...", 
					stockCounts, buyCounts);
			return false;
		}

        // 模拟业务需要3秒
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 2. 创建订单
		boolean isOrderCreated = ordersService.createOrder(itemId);
		
		// 3. 创建订单成功后，扣除库存
		if (isOrderCreated) {
			log.info("订单创建成功...");
			itemsService.displayReduceCounts(itemId, buyCounts);
		} else {
			log.info("订单创建失败...");
			return false;
		}
		
		return true;
	}
}

