package com.ironman.common.DTO;

import lombok.Data;

/**
 * @author lihaodong
 * @create 2020/7/1 7:10 下午
 */
@Data
public class SupportAddressDTO {

    private Long id;
    private String belongTo;
    private String enName;
    private String cnName;
    private String level;
    private double baiduMapLongitude;
    private double baiduMapLatitude;
}
