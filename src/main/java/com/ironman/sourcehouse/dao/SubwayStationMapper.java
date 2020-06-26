package com.ironman.sourcehouse.dao;

import com.ironman.common.base.BaseMapper;
import com.ironman.sourcehouse.model.SubwayStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 11:08
 **/
public interface SubwayStationMapper extends BaseMapper<SubwayStation> {


    List<SubwayStation> getSubwayStation(@Param("subwayId") Long subwayId);


    SubwayStation findById(@Param("id") Long id);

}
