package com.ironman.common.req;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 17:17
 **/
@Data
public class UserReq implements Serializable {
    private String username;
    private String password;


    public static Boolean vaild(UserReq req){
        if (req.getUsername() == null || req.getUsername().equals("") || req.getUsername().length()==0){
            return false;
        }
        if (req.getPassword() ==null || req.getPassword().equals("") || req.getPassword().length()==0) {
            return false;
        }
        return true;
    }

}
