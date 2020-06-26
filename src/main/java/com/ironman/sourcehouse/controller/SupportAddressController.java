package com.ironman.sourcehouse.controller;

import com.ironman.common.DTO.SupportAddressVO;
import com.ironman.common.base.ApiResponse;
import com.ironman.sourcehouse.model.Subway;
import com.ironman.sourcehouse.model.SubwayStation;
import com.ironman.sourcehouse.service.ISubwayService;
import com.ironman.sourcehouse.service.ISupportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 20:24
 **/
@Controller
public class SupportAddressController {

    @Autowired
    ISupportAddress supportAddress;

    @Autowired
    ISubwayService subwayService;


    @GetMapping("/address/support/cities")
    @ResponseBody
    public ApiResponse getCities(){
        List<SupportAddressVO> cities = supportAddress.getCities();
        return ApiResponse.ofSuccess(cities);
    }

    @GetMapping("/address/support/regions")
    @ResponseBody
    public ApiResponse getCities(@RequestParam("city_name") String cityName){
        List<SupportAddressVO> cities = supportAddress.getRegion(cityName);
        return ApiResponse.ofSuccess(cities);
    }
    // /address/support/subway/line?city_name=bj

    @GetMapping("/address/support/subway/line")
    @ResponseBody
    public ApiResponse getSubway(@RequestParam("city_name") String cityName){
        List<Subway> subways = subwayService.getSubways(cityName);
        return ApiResponse.ofSuccess(subways);
    }
    // /address/support/subway/station?subway_id=

    @GetMapping("/address/support/subway/station")
    @ResponseBody
    public ApiResponse getSubwayStation(@RequestParam("subway_id") Long subwayId){
        List<SubwayStation> stations = subwayService.getStations(subwayId);
        return ApiResponse.ofSuccess(stations);
    }
}
