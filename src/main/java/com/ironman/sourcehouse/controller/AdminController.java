package com.ironman.sourcehouse.controller;

import com.ironman.common.DTO.QiNiuPutRet;
import com.ironman.common.base.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 16:40
 **/
@Controller
public class AdminController {

    @GetMapping("/admin/center")
    public String center(){
        return "admin/center";
    }

    @GetMapping("/admin/welcome")
    public String welcome(){
        return "admin/welcome";
    }

    @GetMapping("/admin")
    public String adminIndex(){
        return "admin/login";
    }


    @GetMapping("/admin/add/house")
    public String addHouse(){
        return "admin/house-add";
    }

    @PostMapping(value = "/admin/upload/photo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ApiResponse uploadPhoto(@RequestParam("file")MultipartFile file) throws IOException {
        if (file.getSize() == 0){
            return ApiResponse.ofStatus(ApiResponse.Status.NOT_VALID_PARAM);
        }
        String originalFilename = file.getOriginalFilename();
        File taregt = new File("E:\\ideaproject\\soursehouse\\sourcehourse\\src\\main\\resources\\static\\tmp\\"+originalFilename);
        file.transferTo(taregt);
        QiNiuPutRet qiNiuPutRet = QiNiuPutRet.builder().width(200).width(600).bucket(file.toString()).key(originalFilename).hash("hash").build();
        return ApiResponse.ofSuccess(qiNiuPutRet);
    }

}
