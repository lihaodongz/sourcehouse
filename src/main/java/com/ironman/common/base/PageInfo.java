package com.ironman.common.base;

import lombok.Data;

/**
 * @author lihaodong
 * @create 2020/7/5 4:35 下午
 */
@Data
public class PageInfo {
    private Integer total;

    private Integer page;

    private Integer size;
}
