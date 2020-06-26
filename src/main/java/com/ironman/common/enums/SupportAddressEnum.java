package com.ironman.common.enums;

import springfox.documentation.service.ApiListing;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 20:12
 **/
public enum  SupportAddressEnum {
    city("city"),
    REGION("region")
    ;
    private String level;

    SupportAddressEnum(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
