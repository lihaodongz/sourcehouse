package com.ironman.sourcehouse.service.Imp;

import com.ironman.common.DTO.SupportAddressVO;
import com.ironman.common.enums.SupportAddressEnum;
import com.ironman.sourcehouse.dao.SupportAddressMapper;
import com.ironman.sourcehouse.model.SupportAddress;
import com.ironman.sourcehouse.service.ISupportAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 20:10
 **/
@Service
public class SupportAddressImpl implements ISupportAddress {

    @Autowired
    private SupportAddressMapper supportAddressMapper;



    @Override
    public List<SupportAddressVO> getCities() {
        List<SupportAddress> cities = supportAddressMapper.getByLevel(SupportAddressEnum.city.getLevel());
        return cities.stream().map(this::convert2SupportAddressVO).collect(Collectors.toList());

    }

    @Override
    public List<SupportAddressVO> getRegion(String cityName) {
        if (cityName ==null){

        }
        List<SupportAddress> region = supportAddressMapper.getRegion(cityName, SupportAddressEnum.REGION.getLevel());
        List<SupportAddressVO> collect = region.stream().map(this::convert2SupportAddressVO).collect(Collectors.toList());

        return collect;
    }


    private SupportAddressVO convert2SupportAddressVO(SupportAddress supportAddress){
        SupportAddressVO supportAddressVO = new SupportAddressVO();
        BeanUtils.copyProperties(supportAddress,supportAddressVO);
        return supportAddressVO;
    }
}
