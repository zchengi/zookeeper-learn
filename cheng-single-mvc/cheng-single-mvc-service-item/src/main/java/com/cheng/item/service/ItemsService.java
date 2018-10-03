package com.cheng.item.service;

import com.cheng.pojo.Items;

/**
 * @author cheng
 *         2018/10/3 13:31
 */
public interface ItemsService {

    /**
     * 根据商品id获取商品
     *
     * @param itemId
     * @return
     */
    Items getItem(String itemId);

    /**
     * 查询商品库存
     *
     * @param itemId
     * @return
     */
    int getItemCounts(String itemId);

    /**
     * 购买商品成功后减少库存
     *
     * @param itemId
     * @param buyCounts
     */
    void displayReduceCounts(String itemId, int buyCounts);
}
