package com.cheng.common.enums;

/**
 * 是否枚举
 * @author cheng
 *         2018/10/3 12:23
 */
public enum  YesOrNo {

    /**
     * 是 有错误
     */
    YES(1),
    /**
     * 否 无错误
     */
    NO(0);

    public final int value;

    YesOrNo(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
