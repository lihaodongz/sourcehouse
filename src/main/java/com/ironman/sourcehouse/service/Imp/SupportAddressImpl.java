package com.ironman.sourcehouse.service.Imp;

import com.ironman.common.DTO.SupportAddressDTO;
import com.ironman.common.DTO.SupportAddressVO;
import com.ironman.common.base.ServiceMultiResult;
import com.ironman.common.base.ServiceResult;
import com.ironman.common.enums.SupportAddressEnum;
import com.ironman.common.exception.BusinessException;
import com.ironman.sourcehouse.dao.SupportAddressMapper;
import com.ironman.sourcehouse.model.SupportAddress;
import com.ironman.sourcehouse.service.ISupportAddress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        List<SupportAddress> region = supportAddressMapper.getRegionByName(cityName, SupportAddressEnum.REGION.getLevel());
        List<SupportAddressVO> collect = region.stream().map(this::convert2SupportAddressVO).collect(Collectors.toList());

        return collect;
    }

    @Override
    public Map<SupportAddress.level, SupportAddressDTO> findCityAndRegion(String cityEnName, String regionEnName) {
        Map<SupportAddress.level, SupportAddressDTO> result = new HashMap<>();
        List<SupportAddress> cities = supportAddressMapper.getRegionByRegion(cityEnName, SupportAddressEnum.city.getLevel());
        if (cities != null && !cities.isEmpty()){
            result.put(SupportAddress.level.CITY,convert2SupportAddressDTO(cities.get(0)));
        }
        List<SupportAddress> region = supportAddressMapper.getRegionByName(regionEnName, SupportAddressEnum.REGION.getLevel());
        if (region != null && !region.isEmpty()){
            result.put(SupportAddress.level.REGION,convert2SupportAddressDTO(region.get(0)));
        }
        return result;
    }

    @Override
    public ServiceResult<SupportAddressDTO> findCity(String cityEnName) {
        if (cityEnName == null){
            ServiceResult<SupportAddressDTO> result = ServiceResult.notFound();
        }
        SupportAddress city = supportAddressMapper.getCity(cityEnName, SupportAddressEnum.city.getLevel());
        if (city == null){
            return ServiceResult.notFound();
        }
        return ServiceResult.of(convert2SupportAddressDTO(city));
    }

     //可能查询到为空
    @Override
    public ServiceMultiResult findAllRegionsByCityName(String cityEnName) {
        if (cityEnName == null){
            throw new BusinessException("cityEnName 为空");
        }
        Optional<List<SupportAddress>> regionByName = Optional.of(supportAddressMapper.getRegionByRegion(cityEnName, SupportAddressEnum.REGION.getLevel()));
        if (regionByName.isPresent() || !regionByName.get().isEmpty()){
            return new ServiceMultiResult(regionByName.get().size(),regionByName.get());
        }
        return null;
    }


    private SupportAddressVO convert2SupportAddressVO(SupportAddress supportAddress){
        SupportAddressVO supportAddressVO = new SupportAddressVO();
        BeanUtils.copyProperties(supportAddress,supportAddressVO);
        return supportAddressVO;
    }

    private SupportAddressDTO convert2SupportAddressDTO(SupportAddress supportAddress){
        SupportAddressDTO supportAddressDTO = new SupportAddressDTO();
        BeanUtils.copyProperties(supportAddress,supportAddressDTO);

        return supportAddressDTO;
    }
}
