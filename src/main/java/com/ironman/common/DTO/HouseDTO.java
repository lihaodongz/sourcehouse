package com.ironman.common.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:29
 **/
@Data
public class HouseDTO implements Serializable {



    private Long id;
    private String title;
    private Integer price;
    private Integer area;
    private Integer direction;
    private Integer room;
    private Integer parlour;
    private Integer bathroom;
    private Integer floor;
    private Long adminId;
    private String district;
    private Integer totalFloor;
    private Integer watchTimes;
    private Integer buildYear;
    private Integer status;
    private Date createTime;
    private Date lastUpdateTime;
    private String cityEnName;
    private String regionEnName;
    private String street;
    private String cover;
    private Integer distanceToSubway;
    private  HouseDetailDTO houseDetail;
    private List<String> tags;
    private  List<HousePictureDTO> pictures;
    private Integer subscribeStatus;
    public List<String> getTags() {
        if (this.tags == null) {
            tags = new ArrayList<>();
        }
        return tags;
    }

}
