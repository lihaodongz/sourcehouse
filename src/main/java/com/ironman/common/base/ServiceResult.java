package com.ironman.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/26 18:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResult<T> {
    private Boolean success;
    private String message;
    private T result;


    public ServiceResult(Boolean success){
        this.success =success;
    }

    public ServiceResult(Boolean success,String message){
        this.success =success;
        this.message =message;
    }
    public static <T> ServiceResult<T> success(){ return new ServiceResult<>(true); }

    public static <T> ServiceResult<T> of(T result){
        ServiceResult<T> serviceResult = new ServiceResult<>(true);
        serviceResult.setResult(result);
        return serviceResult;
    }

    public static <T> ServiceResult<T> notFound(){
        return new ServiceResult<>(false,Message.NOT_FOUND.getValue());
    }

    public boolean isSuccess() {
        return success;
    }

    public enum Message{
        NOT_FOUND("not found resource"),
        NOT_LOGIN("not login")
        ;
        private String value;
        Message(String value){
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

}
