package com.ironman.sourcehouse.service;

import com.ironman.common.DTO.SupportAddressVO;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 20:09
 **/
public interface ISupportAddress {

    List<SupportAddressVO >getCities();


   List<SupportAddressVO >getRegion(String cityName);
}
