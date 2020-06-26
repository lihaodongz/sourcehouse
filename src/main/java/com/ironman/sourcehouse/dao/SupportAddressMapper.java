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


    List<SupportAddress> getRegion(@Param("cityName") String cityName,@Param("level") String level);
}
