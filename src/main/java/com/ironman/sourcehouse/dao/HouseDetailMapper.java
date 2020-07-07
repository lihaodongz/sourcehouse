package com.ironman.sourcehouse.dao;

import com.ironman.common.base.BaseMapper;
import com.ironman.sourcehouse.model.HouseDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:09
 **/
public interface HouseDetailMapper extends BaseMapper<HouseDetail> {


    HouseDetail selectByHouseId(@Param("houseId") Integer houseId);


    List<HouseDetail> fetchIds(@Param("ids") List<Long> ids);
}
