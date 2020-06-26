package com.ironman.common.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 20:11
 **/
@Data
public class SupportAddressVO {


    private Long id;

    private String belongTo;

    private String enName;

    private String cnName;

    private String level;

    private Double baiduMapIng;

    private Double baiduMapLat;
}
