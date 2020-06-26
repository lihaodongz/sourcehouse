package com.ironman.sourcehouse.service;

import com.ironman.sourcehouse.model.Subway;
import com.ironman.sourcehouse.model.SubwayStation;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 10:37
 **/
public interface ISubwayService {

    List<Subway> getSubways(String cityName);

    List<SubwayStation> getStations(Long subwayId);
}
