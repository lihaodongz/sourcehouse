package com.ironman.common.base;


import lombok.Data;

/**
 * @author lihaodong
 * @create 2020/6/27 1:10 下午
 */
@Data
public class Sort {

    private String orderBy = "id";

    private String direction = "desc";
}
