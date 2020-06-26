package com.ironman.sourcehouse.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:14
 **/
@Data
@Table(name = "house_tag")
public class HouseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_id")
    private Integer houseId;

    @Column(name = "name")
    private String name;
}
