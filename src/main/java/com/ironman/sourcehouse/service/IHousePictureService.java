package com.ironman.sourcehouse.service;

import com.ironman.common.DTO.HousePictureDTO;

import java.util.List;

/**
 * @author lihaodong
 * @create 2020/7/1 7:22 下午
 */

public interface IHousePictureService {


    /**
     * 查询房屋图片，查到范围信息，查不到返回emptylist
     * @param houseId
     * @return
     */
    List<HousePictureDTO> fetchHousePicture(Integer houseId);

}
