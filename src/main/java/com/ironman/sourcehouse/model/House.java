package com.ironman.sourcehouse.model;

import lombok.Data;

import javax.jnlp.IntegrationService;
import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.Date;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 15:38
 **/
@Data
@Table(name = "house")
public class House {

    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @Column(name = "area")
    private Integer area;

    @Column(name = "room")
    private Integer room;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "total_floor")
    private Integer totalFloor;

    @Column(name = "watch_times")
    private Integer watchTimes;

    @Column(name = "build_year")
    private Integer buildYear;

    @Column(name = "status")
    private  Integer status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    @Column(name = "city_en_name")
    private String cityEnName;

    @Column(name = "region_en_name")
    private String regionEnName;

    @Column(name = "cover")
    private String cover;

    @Column(name = "direction")
    private Integer direction;

    @Column(name = "distance_to_subway")
    private Integer distanceToSubway;

    @Column(name = "parlour")
    private Integer parlour;

    @Column(name = "district")
    private String district;

    @Column(name = "admin_id")
    private Integer adminId;

    @Column(name = "bathroom")
    private Integer bathroom;

    @Column(name = "street")
    private String street;


}
