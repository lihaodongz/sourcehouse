package com.ironman.sourcehouse.controller;

import com.ironman.common.DTO.QiNiuPutRet;
import com.ironman.common.base.ApiResponse;
import com.ironman.common.base.ServiceResult;
import com.ironman.sourcehouse.model.HouseDetail;
import com.ironman.sourcehouse.service.IHouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


    @Autowired
    private IHouseService houseService;


    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

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



    @DeleteMapping("/admin/house/photo")
    @ResponseBody
    public ApiResponse deletePhoto(@RequestParam("id")String id){
        return ApiResponse.ofSuccess(null);
    }


    @PostMapping("/admin/house/cover")
    @ResponseBody
    public ApiResponse conver(){
        return ApiResponse.ofSuccess(null);
    }


    @PutMapping("/admin/house/operate/{houseId}/{id}")
    @ResponseBody
    public ApiResponse operateHouse(@PathVariable(value = "id") String id,@PathVariable(value = "houseId") String houseId){
        logger.info("修改房屋状态 房屋id:{},状态:{}", houseId,id);
        ServiceResult<Boolean> booleanServiceResult = houseService.updateHouseStatus(houseId, id);
        return ApiResponse.ofSuccess(booleanServiceResult);
    }


}
