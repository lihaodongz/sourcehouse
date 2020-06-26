package com.ironman.sourcehouse.controller;

import com.alibaba.fastjson.JSON;
import com.ironman.common.base.BaseResult;
import com.ironman.common.exception.BusinessException;
import com.ironman.common.req.UserReq;
import com.ironman.sourcehouse.service.IUserService;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 17:12
 **/
@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public String login(UserReq userReq){
        logger.info("usercontroller@login.requestparam{}", JSON.toJSONString(userReq,true));
        Boolean vaild = UserReq.vaild(userReq);
        if (!vaild){
          return "admin/login"; }
        BaseResult result = userService.login(userReq);
        if (!result.isSuccess()){
            return "admin/login";
        }
        return "admin/center";
    }
}
