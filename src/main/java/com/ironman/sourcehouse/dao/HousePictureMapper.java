package com.ironman.sourcehouse.dao;

import com.ironman.common.base.BaseMapper;
import com.ironman.sourcehouse.model.HousePicture;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:13
 **/
public interface HousePictureMapper extends BaseMapper<HousePicture> {

    List<HousePicture> fetchByHouseId(@Param("houseId") Integer houseId);
}
