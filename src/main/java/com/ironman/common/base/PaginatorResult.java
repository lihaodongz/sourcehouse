package com.ironman.common.base;

import lombok.Data;

import java.util.List;

/**
 * @author lihaodong
 * @create 2020/7/5 4:34 下午
 */
@Data
public class PaginatorResult <T> {

    private List<T> data;

    private PageInfo pageInfo;



    public static <T> PaginatorResult<T> success(List<T> date,PageInfo pageInfo){
        PaginatorResult<T> result = new PaginatorResult<>();
        result.setData(date);
        result.setPageInfo(pageInfo);
        return result;
    }
}
