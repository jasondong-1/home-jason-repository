package com.ideal.tagportrait.web.controller.hot;

import com.ideal.tagportrait.dto.BarChart;
import com.ideal.tagportrait.entity.Analysis;
import com.ideal.tagportrait.entity.Area;
import com.ideal.tagportrait.entity.Tag;
import com.ideal.tagportrait.framework.web.json.JsonObject;
import com.ideal.tagportrait.service.AreaService;
import com.ideal.tagportrait.service.HotService;
import com.ideal.tagportrait.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/11 10:23
 */
@Controller
@RequestMapping(BaseController.PORTAL_PREFIX + "hot/*")
public class HotController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(HotController.class);

    @Resource
    private HotService hotService;
    @Resource
    private AreaService areaService;

    @RequestMapping("index")
    public void index(Model model) {
        List<Tag> names = hotService.findFirstTags();
        model.addAttribute("tagFirstList", names);
        List<Area> names1 = areaService.findAll();
        model.addAttribute("areaList", names1);
    }

    @RequestMapping("index_second")
    @ResponseBody
    public JsonObject second(String id) {
        logger.debug(String.format("id:%s", id));
        List<Tag> secondTagList = hotService.findSecondTags(Long.parseLong(id));
        JsonObject jsonObject = JsonObject.success(secondTagList);
        return jsonObject;
    }

    @RequestMapping("index_third")
    @ResponseBody
    public JsonObject third(String id) {
        logger.debug(String.format("id:%s",id));
        List<Tag> thirdTagList = hotService.findThirdTags(Long.parseLong(id));
        JsonObject jsonObject = JsonObject.success(thirdTagList);
        return jsonObject;
    }
//    @RequestMapping("index_heartValue")
//    @ResponseBody
//    public JsonObject heartValue(String id) {
//        logger.debug(String.format("id:%s",id));
//        List<Analysis> heartValue = hotService.findHeartValue(Long.parseLong(id));
//        JsonObject jsonObject = JsonObject.success(heartValue);
//        return jsonObject;
//    }
    @RequestMapping("index_heartValueAndCity")
    @ResponseBody
    public JsonObject heartValueAndCity(String id,String city) {
        logger.debug(String.format("id:%s",id)+"city:"+city);
        List<Analysis> heartValue = hotService.findHeartValueAndCity(Long.parseLong(id), city);
        JsonObject jsonObject = JsonObject.success(heartValue);
        return jsonObject;
    }
//    @RequestMapping("show_tag_chart")
//    @ResponseBody
//    public JsonObject showChildrenTagChart(Long id) {
//        BarChart barChart = hotService.getHeatValueTagData(id);
//        JsonObject jsonObject = JsonObject.success(barChart);
//        return jsonObject;
//    }
    @RequestMapping("show_tag_chart_city")
    @ResponseBody
    public JsonObject showHeatValueByCityTagChart(Long id,String city) {
        BarChart barChart = hotService.getHeatValueCityTagData(id, city);
        JsonObject jsonObject = JsonObject.success(barChart);
        return jsonObject;
    }
}
