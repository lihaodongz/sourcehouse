package com.ironman.common.DTO;

import lombok.Data;

/**
 * @author lihaodong
 * @create 2020/6/26 7:51 下午
 */
@Data
public class HouseDetailDTO {
    private String description;
    private String layoutDesc;
    private String traffic;
    private String roundService;
    private Integer rentWay;
    private Long adminId;
    private String address;
    private Long subwayLineId;
    private Long subwayStationId;
    private String subwayLineName;
    private String subwayStationName;


}

