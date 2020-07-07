package com.ironman.common.base;

import lombok.Data;

import java.util.List;

/**
 *
 * @author lihaodong
 * @create 2020/6/27 11:44 上午
 */
@Data
public class ServiceMultiResult<T> {

    private long total;

    private List<T> result;


    public ServiceMultiResult(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }



    public int getResultSize(){
        if (this.result == null){
            return 0;
        }
        return result.size();
    }




}
