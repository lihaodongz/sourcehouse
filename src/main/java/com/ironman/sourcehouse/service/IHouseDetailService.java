package com.ironman.sourcehouse.service;

import com.ironman.common.DTO.HouseDetailDTO;


/**
 * @author lihaodong
 * @create 2020/7/1 8:35 下午
 */
public interface IHouseDetailService {

    HouseDetailDTO fetchHouseDetailDTO(Integer houseId);
}
