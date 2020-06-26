package com.ironman.sourcehouse.service;

import com.ironman.common.DTO.HouseDTO;
import com.ironman.common.base.ServiceResult;
import com.ironman.common.req.HouseForm;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 17:19
 **/
public interface IHouseService {


    ServiceResult<HouseDTO> save(HouseForm houseForm);


}
