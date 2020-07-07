package com.ironman.common.DTO;

import com.ironman.common.base.ApiResponse;
import lombok.Data;

/**
 * @author lihaodong
 * @create 2020/6/27 11:36 上午
 */
@Data
public class ApiDataTableResponse extends ApiResponse {
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;


    public ApiDataTableResponse(ApiResponse.Status status) {
      this(status.getCode(),status.getMessage(),null);
    }


    public ApiDataTableResponse(int code, String message, Object data) {
        super(code, message, data);
    }


}
