package com.ironman.common.DTO;

import lombok.Data;

/**
 * @author lihaodong
 * @create 2020/6/26 7:53 下午
 */
@Data
public class HousePictureDTO {
    private Long id;

    private Long houseId;
    private String path;

    private String cdnPrefix;
    private Integer width;
    private Integer height;

}
