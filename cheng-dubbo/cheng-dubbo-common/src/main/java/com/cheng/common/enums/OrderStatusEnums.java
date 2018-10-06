package com.cheng.common.enums;

/**
 * 订单状态
 *
 * @author cheng
 *         2018/10/3 12:14
 */
public enum OrderStatusEnums {

    /**
     * 代付款
     */
    WAIT_PAY(10, "代付款"),
    /**
     * 付款中
     */
    PAYING(20, "付款中"),
    /**
     * 已付款
     */
    PAID(30, "已付款"),
    /**
     * 付款失败
     */
    PAID_FAILED(40, "付款失败"),
    /**
     * 已取消
     */
    CANCELED(50, "已取消"),
    /**
     * 超时未支付，交易关闭
     */
    CLOSED(60, "交易关闭");

    public final int key;
    public final String value;

    OrderStatusEnums(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static String getName(int key) {
        for (OrderStatusEnums status : OrderStatusEnums.values()) {
            if (status.getKey() == key) {
                return status.value;
            }
        }

        return null;
    }

    public int  getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
