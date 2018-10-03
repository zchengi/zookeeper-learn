package com.cheng.item.service.impl;

import com.cheng.item.service.ItemsService;
import com.cheng.mapper.ItemsMapper;
import com.cheng.pojo.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cheng
 *         2018/10/3 13:31
 */
@Service("itemsService")
public class ItemsServiceImpl implements ItemsService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public Items getItem(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    public int getItemCounts(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId).getCounts();
    }

    @Override
    public void displayReduceCounts(String itemId, int buyCounts) {

        Items reduceItem = new Items();
        reduceItem.setId(itemId);
        reduceItem.setBuyCounts(buyCounts);
        itemsMapper.reduceCounts(reduceItem);
    }
}
