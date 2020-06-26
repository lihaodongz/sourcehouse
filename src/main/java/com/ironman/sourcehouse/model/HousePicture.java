package com.ironman.sourcehouse.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:10
 **/
@Data
@Table(name = "house_picture")
public class HousePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_id")
    private Integer houseId;

    @Column(name = "cdn_prefix")
    private String cdnPrefix;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "location")
    private  String location;

    @Column(name = "path")
    private String path;
}
