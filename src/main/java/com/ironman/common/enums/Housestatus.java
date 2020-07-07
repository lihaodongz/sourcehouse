package com.ironman.common.enums;

public enum Housestatus {

    NOT_VALID(0,"未审核"),
    PASS(1,"通过审核,发布"),
    PULL_OUT(2,"下架。重新审核"),
    DELETE(3," 逻辑删除"),
    RENT(4,"已发布");

    private Integer status;
    private String desc;
    Housestatus(Integer status,String desc){
        this.status =status;
        this.desc =desc;
    }


    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}

