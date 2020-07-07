package com.ironman.sourcehouse.dao;

import com.ironman.common.base.BaseMapper;
import com.ironman.common.base.Paginator;
import com.ironman.common.base.Sort;
import com.ironman.sourcehouse.model.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 16:56
 **/
public interface HouseMapper extends BaseMapper<House> {

    List<House> getWithPaginator(@Param("paginator") Paginator paginator, @Param("sort")Sort sort,@Param("house") House house);

    Integer getCount();


    House findById(@Param("id") String id);


    Integer updateStatus(@Param("id") String id,@Param("status") String status);


}
