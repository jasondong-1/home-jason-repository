package com.ideal.tagportrait.web.controller.dist;

import com.ideal.tagportrait.dto.MapChart;
import com.ideal.tagportrait.entity.Area;
import com.ideal.tagportrait.entity.Tag;
import com.ideal.tagportrait.framework.web.json.JsonObject;
import com.ideal.tagportrait.service.DistService;
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
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Controller
@RequestMapping(BaseController.PORTAL_PREFIX + "dist/*")
public class DistController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(DistController.class);

    @Resource
    private HotService hotService;
    @Resource
    private DistService distService;

    @RequestMapping("index")
    public void index(Model model) {
        List<Tag> names = hotService.findFirstTags();
        model.addAttribute("tagFirstList", names);
    }

    @RequestMapping("index_second")
    @ResponseBody
    public JsonObject second(String id) {
        logger.debug(String.format("id:%s",id));
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

    @RequestMapping("show_map_chart")
    @ResponseBody
    public JsonObject showMapChart(Long tagId) {
        MapChart mapChart = distService.getAnalysisDataByTagId(tagId);
        JsonObject jsonObject = JsonObject.success(mapChart);
        return jsonObject;
    }
}
