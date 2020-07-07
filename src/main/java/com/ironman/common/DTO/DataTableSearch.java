package com.ironman.common.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author lihaodong
 * @create 2020/6/27 11:39 上午
 */
@Data
public class DataTableSearch {

    private int draw;

    private int start = 1;

    private int length = 3;

    private Integer status;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTimeMin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTimeMax;


    private String city;

    private String title;

    private String direction = "id";

    private String orderBy = "desc";


}
