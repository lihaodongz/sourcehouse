package com.ironman.sourcehouse.dao;

import com.ironman.common.base.BaseMapper;
import com.ironman.sourcehouse.model.SupportAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 19:38
 **/
public interface SupportAddressMapper extends BaseMapper<SupportAddress> {


    List<SupportAddress> getByLevel(String levle);


    List<SupportAddress> getRegionByRegion(@Param("cityName") String cityName,@Param("level") String level);


    List<SupportAddress> getRegionByName(@Param("enName") String enName,@Param("level") String level);


    SupportAddress getCity(@Param("cityName") String cityName,@Param("level") String level);
}
