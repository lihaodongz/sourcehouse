package com.ironman.common.base;

/**
 * @author lihaodong
 * @create 2020/6/27 3:06 下午
 */
public enum  HouseStatus {

    NOT_AUDIFED(0), //未审核
    PASSES(1), // 审核通过
    RENTED(2), // 已出租
    DELETED(3) // 逻辑删除

    ;

    private int value;

    HouseStatus(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
