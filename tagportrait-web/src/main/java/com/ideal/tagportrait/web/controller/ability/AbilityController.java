package com.ideal.tagportrait.web.controller.ability;

import com.ideal.tagportrait.dto.BarChart;
import com.ideal.tagportrait.dto.PieChart;
import com.ideal.tagportrait.entity.Area;
import com.ideal.tagportrait.framework.web.json.JsonObject;
import com.ideal.tagportrait.service.AreaService;
import com.ideal.tagportrait.service.PieService;
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
 * @author yuanyuntao
 * @email yuanyuntao@shtel.com.cn
 */
@Controller
@RequestMapping(BaseController.PORTAL_PREFIX + "ability/*")
public class AbilityController {
    protected Logger logger = LoggerFactory.getLogger(AbilityController.class);
    @Resource
    private PieService pieService;
    @Resource
    private AreaService areaService;
    @RequestMapping("index")
    public void index(Model model) {
        List<Area> names1 = areaService.findAll();
        model.addAttribute("areaList", names1);
    }
    /*外环圆*/
    @RequestMapping("show_tag_chart")
    @ResponseBody
    public JsonObject showChildrenTagChart(String tagName,Long areaId) {
        PieChart pieChart = pieService.getTagData(tagName,areaId);
        JsonObject jsonObject = JsonObject.success(pieChart);
        return jsonObject;
    }
    /*内环圆*/
    @RequestMapping("show_firstTag_chart")
    @ResponseBody
    public JsonObject showTagChart(Long areaId) {
        PieChart pieChart = pieService.getFirstTagData(areaId);
        JsonObject jsonObject = JsonObject.success(pieChart);
        return jsonObject;
    }
}
