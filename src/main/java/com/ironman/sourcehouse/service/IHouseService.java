package com.ironman.sourcehouse.service;

import com.ironman.common.DTO.DataTableSearch;
import com.ironman.common.DTO.HouseDTO;
import com.ironman.common.base.ServiceMultiResult;
import com.ironman.common.base.ServiceResult;
import com.ironman.common.base.Paginator;
import com.ironman.common.base.Sort;
import com.ironman.common.req.HouseForm;
import com.ironman.common.req.Rentsearch;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 17:19
 **/
public interface IHouseService {


    ServiceResult<HouseDTO> save(HouseForm houseForm);


    ServiceMultiResult<HouseDTO> adminQuery(DataTableSearch dataTableSearch, Paginator paginator, Sort sort);


    ServiceResult<HouseDTO> findHouseINnfo(String id);


    ServiceResult<Boolean> updateHouseStatus(String houseId,String status);


    /**
     * db 查询接口
     * @param rentSearch
     * @return
     */
    ServiceMultiResult<HouseDTO> query(Rentsearch rentSearch);
}
