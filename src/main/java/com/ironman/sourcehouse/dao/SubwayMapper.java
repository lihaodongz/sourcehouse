package com.ironman.sourcehouse.dao;

import com.ironman.common.base.BaseMapper;
import com.ironman.sourcehouse.model.Subway;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 10:32
 **/
public interface SubwayMapper extends BaseMapper<Subway> {


    List<Subway> getSubByCityName(@Param("cityName") String cityName);

    Subway findById(@Param("id") Long id);
}
