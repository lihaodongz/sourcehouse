package com.ironman.sourcehouse.controller;

import com.alibaba.fastjson.JSON;
import com.ironman.common.DTO.*;
import com.ironman.common.base.*;
import com.ironman.common.req.HouseForm;
import com.ironman.common.req.Rentsearch;
import com.ironman.sourcehouse.model.Subway;
import com.ironman.sourcehouse.model.SubwayStation;
import com.ironman.sourcehouse.model.SupportAddress;
import com.ironman.sourcehouse.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author 李浩东
 * @description
 * @date 2020/6/25 14:30
 **/
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ISupportAddress supportAddress;

    @Autowired
    private IHousePictureService housePictureService;

    @Autowired
    private ITagSerivce tagSerivce;

    @Autowired
    private IHouseDetailService houseDetailService;

    @Autowired
    private ISubwayService subwayService;



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
    @ResponseBody
    public ApiResponse houseAdd(@Valid HouseForm houseForm){
        logger.info(JSON.toJSONString(houseForm,true));
        ServiceResult<HouseDTO> save =
                houseService.save(houseForm);
        return ApiResponse.ofSuccess(save.getResult());
    }


    /**
     * 页面跳转
     * @return
     */
    @GetMapping("/admin/house/list")
    public String getHouseInfoHtml(){
        return "admin/house-list";
    }

    @PostMapping("/admin/houses")
    @ResponseBody
    public ApiDataTableResponse getHouseInfo(DataTableSearch dataTableSearch){

        logger.info("/admin/houses:dataTableSearch:{}",JSON.toJSONString(dataTableSearch,true));
        Paginator paginator = new Paginator();
        paginator.setStart(dataTableSearch.getStart());
        paginator.setSize(dataTableSearch.getLength());
        Sort sort = new Sort();
        sort.setOrderBy(dataTableSearch.getOrderBy());
        sort.setDirection(dataTableSearch.getDirection());
        ServiceMultiResult<HouseDTO> result = houseService.adminQuery(dataTableSearch,paginator,sort);
        logger.info("查询结果为:{}",JSON.toJSONString(result,true));
        ApiDataTableResponse response = new ApiDataTableResponse(ApiResponse.Status.SUCCESS);
        response.setData(result.getResult());
        response.setRecordsTotal(result.getTotal());
        response.setRecordsFiltered(result.getTotal());
        response.setDraw(dataTableSearch.getDraw());
        return response;

    }

    /**
     * 1。 hosueInfo
     * 2.  ity and region
     * 3. picture
     * 4. tags
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/admin/house/edit")
    public String getHouseInfoById(@RequestParam("id") String id, Model model){
        logger.info("/admin/house/edit 请求参数{}",id);
        ServiceResult<HouseDTO> serviceResult = houseService.findHouseINnfo(id);
        HouseDTO houseDTO = serviceResult.getResult();
        Map<SupportAddress.level, SupportAddressDTO> addressMap = supportAddress.findCityAndRegion(houseDTO.getCityEnName(), houseDTO.getRegionEnName());
        SupportAddressDTO city = addressMap.get(SupportAddress.level.CITY);
        SupportAddressDTO region = addressMap.get(SupportAddress.level.REGION);
        model.addAttribute("city", city);
        model.addAttribute("region", region);
        Subway subway = null;
        List<Subway> subways = subwayService.getSubways(houseDTO.getCityEnName());
        if (subways != null || !subways.isEmpty()){
            subway = subways.get(0);
            model.addAttribute("subway",subway);
        }
        List<SubwayStation> stations = subwayService.getStations(subway.getId());
        SubwayStation subwayStation = stations.get(0);
        if (stations != null || !stations.isEmpty()){
            model.addAttribute("station",subwayStation);
        }
        HouseDetailDTO houseDetailDTO = houseDetailService.fetchHouseDetailDTO(houseDTO.getId().intValue());
        houseDTO.setHouseDetail(houseDetailDTO);
        List<HousePictureDTO> pictureDTOS = housePictureService.fetchHousePicture(houseDTO.getId().intValue());
        if (!pictureDTOS.isEmpty()){
            houseDTO.setPictures(pictureDTOS);
        }
        List<String> tag = tagSerivce.getTag(houseDTO.getId());
        if (!tag.isEmpty()){
            houseDTO.setTags(tag);
        }
        logger.info("house:{}",JSON.toJSONString(houseDTO,true));
        model.addAttribute("house",houseDTO);
        return "admin/house-edit";
    }



    @PostMapping("/admin/house/edit")
    @ResponseBody
    public ApiResponse editHouse( HouseForm houseForm){
        logger.info("修改的房屋信息:{}",JSON.toJSONString(houseForm,true));
        return ApiResponse.ofSuccess(null);
    }


    @GetMapping("/rent/house")
    public String rentHousePage(Rentsearch rentSearch, Model model, RedirectAttributes redirectAttributes, HttpSession session){
        logger.info("查询请求参数:{}",JSON.toJSONString(rentSearch,true));
        if (rentSearch.getCityEnName() == null) {
            String cityEnNameInSession = (String) session.getAttribute("cityEnName");
            if (cityEnNameInSession == null) {
                redirectAttributes.addAttribute("msg", "must_chose_city");
                return "redirect:/index";
            } else {
                rentSearch.setCityEnName(cityEnNameInSession);
            }
        } else {
            session.setAttribute("cityEnName", rentSearch.getCityEnName());
        }

        ServiceResult<SupportAddressDTO> city = supportAddress.findCity(rentSearch.getCityEnName());
        if (!city.isSuccess()) {
            redirectAttributes.addAttribute("msg", "must_chose_city");
            return "redirect:/index";
        }
        model.addAttribute("currentCity", city.getResult());

        ServiceMultiResult addressResult = supportAddress.findAllRegionsByCityName(rentSearch.getCityEnName());
        if (addressResult.getResult() == null || addressResult.getTotal() < 1) {
            redirectAttributes.addAttribute("msg", "must_chose_city");
            return "redirect:/index";
        }

        ServiceMultiResult<HouseDTO> serviceMultiResult = houseService.query(rentSearch);

        model.addAttribute("total", serviceMultiResult.getTotal());
        model.addAttribute("houses", serviceMultiResult.getResult());

        if (rentSearch.getRegionEnName() == null) {
            rentSearch.setRegionEnName("*");
        }

        model.addAttribute("searchBody", rentSearch);
        model.addAttribute("regions", addressResult.getResult());

        model.addAttribute("priceBlocks", RentValueBlock.PRICE_BLOCK);
        model.addAttribute("areaBlocks", RentValueBlock.AREA_BLOCK);

        model.addAttribute("currentPriceBlock", RentValueBlock.matchPrice(rentSearch.getPriceBlock()));
        model.addAttribute("currentAreaBlock", RentValueBlock.matchArea(rentSearch.getAreaBlock()));

        return "rent-list";
    }

}
