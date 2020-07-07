package com.ironman.sourcehouse.service.Imp;

import com.ironman.common.DTO.HousePictureDTO;
import com.ironman.common.exception.BusinessException;
import com.ironman.sourcehouse.dao.HousePictureMapper;
import com.ironman.sourcehouse.model.HousePicture;
import com.ironman.sourcehouse.service.IHousePictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author lihaodong
 * @create 2020/7/1 7:22 下午
 */
@Service
public class HousePictureServiceImpl implements IHousePictureService {

    private static final Logger logger = LoggerFactory.getLogger(HousePictureServiceImpl.class);

    @Resource
    private HousePictureMapper housePictureMapper;



    @Override
    public List<HousePictureDTO> fetchHousePicture(Integer houseId) {
        Optional<List<HousePicture>> housePictures = Optional.empty();
        List<HousePictureDTO> collect = Collections.emptyList();
        if (houseId == null){
            return Collections.emptyList();
        }
        try {
            housePictures = Optional.of(housePictureMapper.fetchByHouseId(houseId));
        }catch (Exception e){
            logger.error("housePictureMapper#selectByCondition 查询房屋图片数据库执行失败{}",e.getMessage());
            throw new BusinessException();
        }
        if (housePictures.isPresent()) {
           collect = housePictures.get().stream().map(this::convert2HousePictureDTO).collect(Collectors.toList());

        }

        return collect;
    }


    private HousePictureDTO convert2HousePictureDTO(HousePicture housePicture){
        HousePictureDTO housePictureDTO = new HousePictureDTO();
        BeanUtils.copyProperties(housePicture,housePictureDTO);
        return housePictureDTO;
    }
}
