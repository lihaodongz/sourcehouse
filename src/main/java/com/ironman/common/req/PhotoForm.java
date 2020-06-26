package com.ironman.common.req;

import lombok.Data;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 14:31
 **/
@Data
public class PhotoForm {
    private String path;

    private int width;

    private int height;
}
