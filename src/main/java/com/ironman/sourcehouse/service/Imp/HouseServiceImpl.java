package com.ironman.sourcehouse.service.Imp;

import com.alibaba.fastjson.JSON;
import com.ironman.common.DTO.DataTableSearch;
import com.ironman.common.DTO.HouseDTO;
import com.ironman.common.DTO.HouseDetailDTO;
import com.ironman.common.DTO.HousePictureDTO;
import com.ironman.common.base.ServiceMultiResult;
import com.ironman.common.base.ServiceResult;
import com.ironman.common.base.Paginator;
import com.ironman.common.base.Sort;
import com.ironman.common.enums.Housestatus;
import com.ironman.common.exception.BusinessException;
import com.ironman.common.req.HouseForm;
import com.ironman.common.req.PhotoForm;
import com.ironman.common.req.Rentsearch;
import com.ironman.sourcehouse.dao.*;
import com.ironman.sourcehouse.model.*;
import com.ironman.sourcehouse.service.IHouseService;
import jdk.management.resource.SimpleMeter;
import org.apache.shiro.authz.HostUnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 17:20
 **/
@Service
public class HouseServiceImpl implements IHouseService {

    private static final Logger logger = LoggerFactory.getLogger(HouseServiceImpl.class);


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
    @Transactional
    public ServiceResult<HouseDTO> save(HouseForm houseForm) {
        HouseDetail houseDetail = new HouseDetail();
        ServiceResult<HouseDTO> serviceResult = wrapperDetailInfo(houseDetail, houseForm);
        if (serviceResult != null){
            return serviceResult;
        }
        House house = new House();
        BeanUtils.copyProperties(houseForm,house);
        Date date = new Date();
        house.setCreateTime(date);
        house.setLastUpdateTime(date);
        house.setAdminId(-1);
        house.setWatchTimes(0);
        house.setStatus(0);
        house.setBathroom(0);
        logger.info(JSON.toJSONString(house,true));
        houseMapper.insert(house);
        logger.info("插入house后:{}",JSON.toJSONString(house,true));
        Long houseId = house.getId();
        //Long houseId = houseMapper.select(house).get(0).getId();
        houseDetail.setHouseId(houseId.intValue());
        logger.info(JSON.toJSONString(houseDetail,true));
        houseDetailMapper.insert(houseDetail);
        List<HousePicture> housePictures = generatePicture(houseForm, houseId);
        logger.info(JSON.toJSONString(housePictures,true));
        housePictureMapper.insertList(housePictures);
        HouseDTO houseDTO = new HouseDTO();
        BeanUtils.copyProperties(house,houseDTO);
        HouseDetailDTO houseDetailDTO = new HouseDetailDTO();
        BeanUtils.copyProperties(house,houseDetailDTO);
        houseDTO.setHouseDetail(houseDetailDTO);
        List<HousePictureDTO>  pictureDTOS = new ArrayList<>();
        logger.info("housePictures:{}",JSON.toJSONString(housePictures,true));
        List<HousePictureDTO> collect = housePictures.stream()
                .map(this::convert2HousePictureDTO2)
                .collect(Collectors.toList());
        pictureDTOS.addAll(collect);
        houseDTO.setPictures(pictureDTOS);
        houseDTO.setCover(this.cdnPrefix+houseDTO.getCover());
        List<String> tags = houseForm.getTags();
        if (tags != null && !tags.isEmpty()){
            List<HouseTag> houseTags = new ArrayList<>();
            for (String tag : tags) {
                HouseTag houseTag = new HouseTag();
                houseTag.setHouseId(houseId.intValue());
                houseTag.setName(tag);
                houseTags.add(houseTag);
            }
            logger.info(JSON.toJSONString(houseTags,true));
            houseTagMapper.insertList(houseTags);
            houseDTO.setTags(tags);
        }
        return new ServiceResult<>(true,null,houseDTO);
    }

    @Override
    public ServiceMultiResult<HouseDTO> adminQuery(DataTableSearch dataTableSearch, Paginator paginator, Sort sort) {

       List<HouseDTO> houseDTOList = new ArrayList<>();
        House house1 = new House();
        if (dataTableSearch.getCity() != null){
            house1.setCityEnName(dataTableSearch.getCity());
        }
        if (dataTableSearch.getStatus() != null){
            house1.setStatus(dataTableSearch.getStatus());
        }
        if (dataTableSearch.getTitle() != null){
            house1.setTitle(dataTableSearch.getTitle());
        }
        logger.info("sortAndPaginator:{}",JSON.toJSONString(paginator,true));
        List<House> houses = houseMapper.getWithPaginator(paginator,sort,house1);
        logger.info("查询的结果信息：{}",JSON.toJSONString(houses,true));
        try {
            houses.forEach(house -> {
                HouseDTO houseDTO = new HouseDTO();
                BeanUtils.copyProperties(house,houseDTO);
                houseDTO.setCover(cdnPrefix+house.getCover());
                houseDTOList.add(houseDTO);
            });
        }catch (Exception e){
            logger.info("异常信息为：{}",e.getMessage());
        }
        Integer count = houseMapper.getCount();
        return new ServiceMultiResult<>(count,houseDTOList);
    }

    @Override
    public ServiceResult<HouseDTO> findHouseINnfo(String id) {
        if (Integer.valueOf(id) <= 0){
            return new ServiceResult<>();
        }
        House house = new House();
        house.setId(Long.valueOf(id));
        house = houseMapper.selectByPrimaryKey(house);
        HouseDTO houseDTO = new HouseDTO();
        BeanUtils.copyProperties(house,houseDTO);
        return ServiceResult.of(houseDTO);
    }

    /**
     * 注意修改属性状态的接口，修改状态时不能随意修改，会存在状态之间的依赖关系。
     * 比如 商家的商品是不可以进行状态修改的，而且商品状态的改变会触发商品状态的变更，比如商品删除，此时会讲商品从搜索引擎里面剔除。
     * 1. 查询当前房屋信息
     * 2. 校验状态看是否是支持的状态
     * 3. 看看当前的商品状态和要修改的状态是否存在冲突
     * 4. 修改成功之后发送消息，下游系统进行相应的消息消费
     * @param houseId
     * @param status
     * @return
     */
    @Override
    public ServiceResult<Boolean> updateHouseStatus(String houseId, String status) {
        House house = houseMapper.findById(houseId);
        if (house != null){
            Integer oldStatus = house.getStatus();
            if (oldStatus.equals(status)){
                return new ServiceResult<>();
            }
            // TODO: 2020/7/5  做规则的校验，已上架商品不能审核通过，商品必须下架之后才可以进行审核。
            if (oldStatus.equals(Housestatus.RENT.getStatus())){
                throw new BusinessException("商品处于商家状态,暂不支持此类型操作。");
            }
            try {
                houseMapper.updateStatus(houseId, status);
                return ServiceResult.success();
            }catch (Exception e){
                logger.error("houseMapper#updateStatus 发生异常 {}",JSON.toJSONString(e.getMessage()));
            }
        }
        throw new BusinessException("房屋信息不存在");
    }

    @Override
    public ServiceMultiResult<HouseDTO> query(Rentsearch rentSearch) {
        List<HouseDTO> houseDTOList = new ArrayList();
        if (rentSearch == null){
            throw new BusinessException("请求参数为空");
        }
        Sort sort = new Sort();
        if (rentSearch.getOrderBy() != null){
            sort.setOrderBy(rentSearch.getOrderBy());
        }
        if (rentSearch.getOrderDirection() !=null){
            sort.setDirection(rentSearch.getOrderDirection());
        }
        Paginator paginator = new Paginator();
        paginator.setStart(rentSearch.getStart());
        paginator.setSize(rentSearch.getSize());
        House house1 = new House();
        if (rentSearch.getCityEnName() != null){
            house1.setCityEnName(rentSearch.getCityEnName());
        }
        if (rentSearch.getRegionEnName() != null){
            house1.setRegionEnName(rentSearch.getRegionEnName());
        }
        if (rentSearch.getKeywords() != null){
            house1.setTitle(rentSearch.getKeywords());
        }
        List<House> houses = houseMapper.getWithPaginator(paginator,sort,house1);
        List<Long> ids = houses.stream().map(House::getId).collect(Collectors.toList());
        List<HouseDetail> houseDetails = houseDetailMapper.fetchIds(ids);
        logger.info("ids 查询结果为:{}",JSON.toJSONString(houseDetails,true));
        Map<Integer, HouseDetail> houseDetailMap = houseDetails.stream().collect(Collectors.toMap(HouseDetail::getHouseId,Function.identity(),(f1,f2)->f1));
        logger.info("houseDetailMap:{}",JSON.toJSONString(houseDetailMap,true));
        try {
            houses.forEach(house -> {
                HouseDTO houseDTO = new HouseDTO();
                BeanUtils.copyProperties(house,houseDTO);
                houseDTO.setCover(cdnPrefix+house.getCover());
                houseDTOList.add(houseDTO);
            });
        }catch (Exception e){
            logger.info("异常信息为：{}",e.getMessage());
        }

        for (HouseDTO houseDTO : houseDTOList) {
            // map中取出结果
            HouseDetail houseDetail = houseDetailMap.get(houseDTO.getId().intValue());
            if (houseDetail != null){
                houseDTO.setHouseDetail(conver2HouseDetailDTO(houseDetail));
            }
        }
        int count = houseMapper.getCount();
        logger.info("返回结果为：{}",JSON.toJSONString(houseDTOList,true));
        return new ServiceMultiResult<>(count,houseDTOList);
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
            housePicture.setPath(photo.getPath());
            housePictures.add(housePicture);
        }
        return housePictures;

    }

    private static HouseDetailDTO conver2HouseDetailDTO(HouseDetail houseDetail){
        HouseDetailDTO houseDetailDTO = new HouseDetailDTO();
        BeanUtils.copyProperties(houseDetail,houseDetailDTO);
        return houseDetailDTO;
    }




    private HousePictureDTO convert2HousePictureDTO2(HousePicture housePicture){
        logger.info("方法执行前:{}",JSON.toJSONString(housePicture,true));
        HousePictureDTO housePictureDTO = new HousePictureDTO();
        try {
            BeanUtils.copyProperties(housePicture,housePictureDTO);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new BusinessException("拷贝失败");
        }
        return housePictureDTO;
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


        houseDetail.setAddress(houseForm.getDetailAddress());
        houseDetail.setDescription(houseForm.getDescription());
        houseDetail.setLayoutDesc(houseForm.getLayoutDesc());
        houseDetail.setRentWay(houseForm.getRentWay());
        houseDetail.setRoundService(houseForm.getRoundService());
        houseDetail.setTraffic(houseDetail.getTraffic());
        return null;

    }
}
