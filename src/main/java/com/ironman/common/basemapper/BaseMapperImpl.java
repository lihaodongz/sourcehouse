package com.ironman.common.basemapper;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.ironman.common.base.Paginator;
import com.ironman.common.base.PaginatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author lihaodong
 * @create 2020/7/5 4:19 下午
 * 此操作类为基础的mapper 类，用来封装一些基础的操作方法。需要使用的的类直接继承该类，然后调用方法即可。
 */
public class BaseMapperImpl implements BaseMapper {

    private final Logger logger = LoggerFactory.getLogger(BaseMapperImpl.class);

    /**
     * {
     *     data{
     *         items:[{},{}],
     *         paginator:{
     *             total:xxx,
     *             page:xxx,
     *             size:xxx
     *         }
     *     }
     * }
     *
     */
    @Override
    public PaginatorResult selectPaginator(SelectPaginatorInterface selectPaginatorInterface, Paginator paginator) {
        PageHelper.startPage(paginator.getStart(),paginator.getSize());
        try {
            List list = Collections.singletonList(selectPaginatorInterface.selectPaginator());
            PaginatorResult success = PaginatorResult.success(list, null);
            return success;
        }catch (Exception e){
            logger.error("分页查询失败,{}", JSON.toJSONString(e.getMessage(),true));
        }
        return null;
    }
}
