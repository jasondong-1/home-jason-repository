package com.ideal.tagportrait.web.controller.ability;

import com.ideal.tagportrait.dto.BarChart;
import com.ideal.tagportrait.dto.PieChart;
import com.ideal.tagportrait.framework.web.json.JsonObject;
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
    @RequestMapping("index")
    public void index(Model model) {
        /*List<PlatformForm> platformFormList = mainService.getPlatformInfo();
        model.addAttribute("platforms",platformFormList);*/
        // List<String> names = mainService.findStudentNames();
        //model.addAttribute("hqlList", names);
    }
    @RequestMapping("show_tag_chart")
    @ResponseBody
    public JsonObject showChildrenTagChart(String tagName) {
        PieChart pieChart = pieService.getTagData(tagName);
        JsonObject jsonObject = JsonObject.success(pieChart);
        return jsonObject;
    }
}
