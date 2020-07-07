package com.ironman.common.basemapper;


import java.util.List;

/**
 * @author lihaodong
 * @create 2020/7/5 4:21 下午
 */

public interface SelectPaginatorInterface<T> {

    List<T> selectPaginator();
}
