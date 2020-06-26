package com.ironman.common.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:29
 **/
@Data
public class HouseDTO {


    private Long id;

    private String title;

    private Integer price;

    private Integer area;


    private Integer room;


    private Integer floor;

    private Integer totalFloor;


    private Integer watchTimes;


    private Integer buildYear;


    private  Integer status;


    private Date createTime;


    private Date lastUpdateTime;

    private String cityEnName;


    private String regionEnName;


    private String cover;

    private Integer direction;


    private Integer distanceToSub;


    private Integer parlour;


    private String district;


    private Integer adminId;


    private Integer bathroom;

    private String shreet;
}
