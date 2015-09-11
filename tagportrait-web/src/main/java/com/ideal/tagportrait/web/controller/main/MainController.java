package com.ideal.tagportrait.web.controller.main;

import com.ideal.tagportrait.dto.BarChart;
import com.ideal.tagportrait.dto.TreeNode;
import com.ideal.tagportrait.entity.Tag;
import com.ideal.tagportrait.entity.Area;
import com.ideal.tagportrait.framework.web.json.JsonObject;
import com.ideal.tagportrait.service.AreaService;
import com.ideal.tagportrait.web.controller.BaseController;
import com.ideal.tagportrait.service.MainService;
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
 * @mail 18918567223@189.cn
 * created on 2015/09/09 18:01
 */

@Controller
@RequestMapping(BaseController.PORTAL_PREFIX + "main/*")
public class MainController extends BaseController {
    protected Logger logger = LoggerFactory.getLogger(MainController.class);

    @Resource
    private MainService mainService;
    @Resource
    private AreaService areaService;

    @RequestMapping("index")
    public void index(Model model) {
        List<Area> areaList = areaService.findAll();
        model.addAttribute("areaList", areaList);
    }


    @RequestMapping("tag_tree")
    @ResponseBody
    public List<TreeNode> showTagTree(String id, Long areaId) {
        logger.info(String.format("id:%s", id));
        logger.info(String.format("areaId:%s", areaId));
        List<TreeNode> treeNodeList = mainService.getTagTree(id, areaId);
        return treeNodeList;
    }

    @RequestMapping("show_tag_chart")
    @ResponseBody
    public JsonObject showChildrenTagChart(String tagId, Long areaId) {
        BarChart barChart = mainService.getChildrenTagData(tagId, areaId);
        JsonObject jsonObject = JsonObject.success(barChart);
        return jsonObject;
    }

    @RequestMapping("save_tag_description")
    public void saveTagDescription(String tagId, String description) {
        mainService.saveTagDescription(tagId, description);
    }

    @RequestMapping("query_tag_description")
    @ResponseBody
    public JsonObject queryTagDescription(String tagId) {
        Tag tag = mainService.getTag(tagId);
        JsonObject jsonObject = JsonObject.success(tag);
        return jsonObject;
    }

}
