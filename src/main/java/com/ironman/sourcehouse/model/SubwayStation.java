package com.ironman.sourcehouse.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 11:06
 **/
@Data
@Table(name = "subway_station")
public class SubwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subway_id")
    private String subwayId;

    @Column(name = "name")
    private String name;
}
