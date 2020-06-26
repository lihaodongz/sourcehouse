package com.ironman.sourcehouse.controller;

import com.alibaba.fastjson.JSON;
import com.ironman.common.req.HouseForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 14:30
 **/
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @GetMapping("/")
    public String getHome(){
        return "index";
    }


    /**
     * 1. house,housedetail,houseimage,housetag 都要入库
     * @param houseForm
     * @return
     */
    @PostMapping("/admin/add/house")
    public String houseAdd(@Valid HouseForm houseForm){
        logger.info(JSON.toJSONString(houseForm,true));
        return "/admin/center";
    }


}
