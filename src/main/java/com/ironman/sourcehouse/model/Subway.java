package com.ironman.sourcehouse.model;

import lombok.Data;
import javax.persistence.*;


/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 10:30
 **/
@Data
@Table(name = "subway")
public class Subway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "city_en_name")
    private String cityEnName;
}
