package com.ironman.common.basemapper;

import com.ironman.common.base.Paginator;
import com.ironman.common.base.PaginatorResult;

/**
 * @author lihaodong
 * @create 2020/7/5 4:18 下午
 */
public interface BaseMapper {

    PaginatorResult selectPaginator(SelectPaginatorInterface selectPaginatorInterface,
                                    Paginator paginator);
}
