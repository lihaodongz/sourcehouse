package com.ironman.sourcehouse.service;

import com.ironman.common.DTO.SupportAddressDTO;
import com.ironman.common.DTO.SupportAddressVO;
import com.ironman.common.base.ServiceMultiResult;
import com.ironman.common.base.ServiceResult;
import com.ironman.sourcehouse.model.SupportAddress;

import java.util.List;
import java.util.Map;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 20:09
 **/
public interface ISupportAddress {

    List<SupportAddressVO >getCities();


   List<SupportAddressVO >getRegion(String cityName);

    Map<SupportAddress.level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName);

    ServiceResult<SupportAddressDTO> findCity(String cityEnName);

    ServiceMultiResult findAllRegionsByCityName(String cityEnName);
}
