package com.cheng.common.enums;

/**
 * 男女枚举
 *
 * @author cheng
 *         2018/10/3 12:22
 */
public enum SexEnum {

    /**
     * 女
     */
    GIRL(0),
    /**
     * 男
     */
    BOY(1),
    /**
     * 保密
     */
    SECRET(2);

    public final int value;

    SexEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
