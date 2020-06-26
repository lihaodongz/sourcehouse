package com.ironman.common.exception;

import com.ironman.common.base.BaseResult;
import com.ironman.common.base.BaseResultGenerator;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 17:29
 **/
@ControllerAdvice
public class GlobalException {



    @ExceptionHandler(BusinessException.class)
    public BaseResult handlerBusinession(Exception e){
        if (e instanceof  BusinessException){
            BusinessException businessException = (BusinessException)e;
            return BaseResultGenerator.error(businessException);
        }

        return BaseResultGenerator.failure();
    }
}
