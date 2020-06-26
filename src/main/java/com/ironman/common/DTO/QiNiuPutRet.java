package com.ironman.common.DTO;

import lombok.Builder;
import lombok.Data;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 11:46
 **/
@Data
@Builder
public class QiNiuPutRet {
    public String key;
    public String hash;
    public String bucket;
    public int width;
    public int height;

}
