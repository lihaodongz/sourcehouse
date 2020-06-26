package com.ironman.sourcehouse.service.Imp;

import com.ironman.sourcehouse.dao.SubwayMapper;
import com.ironman.sourcehouse.dao.SubwayStationMapper;
import com.ironman.sourcehouse.model.Subway;
import com.ironman.sourcehouse.model.SubwayStation;
import com.ironman.sourcehouse.service.ISubwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 10:37
 **/
@Service
public class SuwayServiceImpl implements ISubwayService {

    @Autowired
    private SubwayMapper subwayMapper;

    @Autowired
    private SubwayStationMapper subwayStationMapper;


    @Override
    public List<Subway> getSubways(String cityName) {
        if (cityName == null){
            return null;
        }
        List<Subway> subByCityName = subwayMapper.getSubByCityName(cityName);
        return subByCityName;
    }

    @Override
    public List<SubwayStation> getStations(Long subwayId) {
        if (subwayId == null){
            return null;
        }
        List<SubwayStation> subwayStation = subwayStationMapper.getSubwayStation(subwayId);
        return subwayStation;
    }
}
