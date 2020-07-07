package com.ironman.sourcehouse.dao;

import com.ironman.common.base.BaseMapper;
import com.ironman.sourcehouse.model.HouseTag;
import org.apache.catalina.startup.HostRuleSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:15
 **/
public interface HouseTagMapper extends BaseMapper<HouseTag> {

    List<HouseTag> fetchTags(@Param("houseId") Integer houseId);
}
