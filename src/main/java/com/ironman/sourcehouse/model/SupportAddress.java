package com.ironman.sourcehouse.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 19:33
 **/
@Data
@Table(name = "support_address")
public class SupportAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "belong_to")
    private String belongTo;
    @Column(name = "en_name")
    private String enName;
    @Column(name = "cn_name")
    private String cnName;
    @Column(name = "level")
    private String level;
    @Column(name = "baidu_map_ing")
    private Double baiduMapIng;
    @Column(name = "baidu_map_lat")
    private Double baiduMapLat;

}
