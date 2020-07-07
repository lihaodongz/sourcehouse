package com.ironman.common.req;

import lombok.Data;

/**
 * @author lihaodong
 * @create 2020/7/5 3:35 下午
 */
@Data
public class Rentsearch {

    private String cityEnName;
    private String regionEnName;
    private String priceBlock;
    private String areaBlock;
    private int room;
    private int direction;
    private String keywords;
    private int rentWay = -1;
    private String orderBy = "last_update_time";
    private String orderDirection = "desc";
    private int start = 0;
    private int size = 5;

    public int getSize() {
        if (this.size < 1) {
            return 5;
        } else if (this.size > 100) {
            return 100;
        } else {
            return this.size;
        }
    }

    public int getRentWay() {
        if (rentWay > -2 && rentWay < 2) {
            return rentWay;
        } else {
            return -1;
        }
    }

}
