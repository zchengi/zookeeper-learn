package com.cheng.web.service;

/**
 * @author cheng
 *         2018/10/3 13:50
 */
public interface BuyService {

    /**
     * 购买商品
     *
     * @param itemId
     */
    void doBuyItem(String itemId);

    /**
     * 判断库存
     *
     * @param itemId
     * @return
     */
    boolean displayBuy(String itemId);
}

