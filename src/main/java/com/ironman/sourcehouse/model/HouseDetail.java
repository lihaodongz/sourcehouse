package com.ironman.sourcehouse.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:00
 **/
@Data
@Table(name = "house_detail")
public class HouseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "layout_desc")
    private String layoutDesc;

    @Column(name = "traffic")
    private String traffic;

    @Column(name = "round_service")
    private String roundService;

    @Column(name = "rent_way")
    private Integer rentWay;

    @Column(name = "address")
    private String address;

    @Column(name = "subway_line_id")
    private Integer subwayLineId;

    @Column(name = "subway_line_name")
    private String subwayLineName;

    @Column(name = "subway_station_id")
    private Integer subwayStationId;

    @Column(name = "subway_station_name")
    private String subwayStationName;

    @Column(name = "house_id")
    private Integer houseId;

}
