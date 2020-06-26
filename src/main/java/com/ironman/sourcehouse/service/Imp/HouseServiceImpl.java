package com.ironman.sourcehouse.service.Imp;

import com.ironman.common.DTO.HouseDTO;
import com.ironman.common.base.ServiceResult;
import com.ironman.common.req.HouseForm;
import com.ironman.common.req.PhotoForm;
import com.ironman.sourcehouse.dao.*;
import com.ironman.sourcehouse.model.*;
import com.ironman.sourcehouse.service.IHouseService;
import io.lettuce.core.ConnectionEvents;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 17:20
 **/
@Service
public class HouseServiceImpl implements IHouseService {

    private final String cdnPrefix = "http://127.0.0.1:10002/tmp";

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseDetailMapper houseDetailMapper;

    @Autowired
    private HousePictureMapper housePictureMapper;

    @Autowired
    private HouseTagMapper houseTagMapper;

    @Autowired
    private SubwayMapper subwayMapper;

    @Autowired
    private SubwayStationMapper subwayStationMapper;



    @Override
    public ServiceResult<HouseDTO> save(HouseForm houseForm) {
        HouseDetail houseDetail = new HouseDetail();
        ServiceResult<HouseDTO> serviceResult = wrapperDetailInfo(houseDetail, houseForm);
        if (serviceResult != null){
            return serviceResult;
        }
        House house = new House();
        BeanUtils.copyProperties(houseDetail,house);
        Date date = new Date();
        house.setCreateTime(date);
        house.setLastUpdateTime(date);
        house.setAdminId(-1);
        houseMapper.insert(house);
        Long houseId = houseMapper.select(house).get(0).getId();
        houseDetail.setHouseId(houseId.intValue());

        houseDetailMapper.insert(houseDetail);

        List<HousePicture> housePictures = generatePicture(houseForm, houseId);

        housePictureMapper.insertList(housePictures);


        HouseDTO houseDTO = new HouseDTO();

        BeanUtils.copyProperties(house,houseDTO);



        return new ServiceResult<>(true,null,houseDTO);
    }


    private List<HousePicture> generatePicture(HouseForm houseForm, Long houseId){
        List<HousePicture> housePictures = new ArrayList<>();
        if (houseForm.getPhotos() == null || houseForm.getPhotos().isEmpty()){
            return housePictures;
        }
        for (PhotoForm photo : houseForm.getPhotos()) {
            HousePicture housePicture = new HousePicture();
            housePicture.setCdnPrefix(cdnPrefix);
            housePicture.setHouseId(houseId.intValue());
            housePicture.setWidth(photo.getWidth());
            housePicture.setWidth(photo.getWidth());
            housePictures.add(housePicture);
        }
        return housePictures;

    }

    private ServiceResult<HouseDTO> wrapperDetailInfo(HouseDetail houseDetail,HouseForm houseForm){
        Subway subway = subwayMapper.findById(houseForm.getSubwayLineId());
        if (subway == null){
            return new ServiceResult<>(false,"not valid subway line");
        }
        SubwayStation subwayStation = subwayStationMapper.findById(houseForm.getSubwayStationId());
        if (subwayStation == null){
            return new ServiceResult<>(false,"not valid subwaystation l");
        }

        houseDetail.setSubwayLineId(subway.getId().intValue());
        houseDetail.setSubwayLineName(subway.getName());

        houseDetail.setSubwayStationId(subwayStation.getId().intValue());
        houseDetail.setSubwayStationName(subwayStation.getName());


        houseDetail.setDescription(houseForm.getDescription());
        houseDetail.setLayoutDesc(houseForm.getLayoutDesc());
        houseDetail.setRentWay(houseForm.getRentWay());
        houseDetail.setRoundService(houseForm.getRoundService());
        houseDetail.setTraffic(houseDetail.getTraffic());
        return null;

    }
}
