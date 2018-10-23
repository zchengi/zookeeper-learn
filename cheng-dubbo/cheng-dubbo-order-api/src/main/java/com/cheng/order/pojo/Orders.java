package com.cheng.order.pojo;

import java.io.Serializable;

/**
 * @author cheng
 *         2018/10/3 12:41
 */
public class Orders implements Serializable {

    private static final long serialVersionUID = 6642958741804500474L;

    private String id;

    private String orderNum;

    private String itemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }
}