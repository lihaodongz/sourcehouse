package com.ironman.sourcehouse.service.Imp;

import com.ironman.common.DTO.HouseDetailDTO;
import com.ironman.sourcehouse.dao.HouseDetailMapper;
import com.ironman.sourcehouse.model.House;
import com.ironman.sourcehouse.model.HouseDetail;
import com.ironman.sourcehouse.service.IHouseDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lihaodong
 * @create 2020/7/1 8:36 下午
 */
@Service
public class HouseDetailServiceImpl implements IHouseDetailService {

    @Autowired
    private HouseDetailMapper houseDetailMapper;


    @Override
    public HouseDetailDTO fetchHouseDetailDTO(Integer houseId) {
        if (houseId == null){
            return null;
        }
        HouseDetail houseDetail = houseDetailMapper.selectByHouseId(houseId);

        return convert2HouseDetailDTO(houseDetail);
    }

    private HouseDetailDTO convert2HouseDetailDTO(HouseDetail houseDetail){
        HouseDetailDTO houseDetailDTO = new HouseDetailDTO();
        BeanUtils.copyProperties(houseDetail,houseDetailDTO);
        return houseDetailDTO;
    }


}
