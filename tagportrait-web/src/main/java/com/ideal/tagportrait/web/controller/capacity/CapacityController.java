package com.ideal.tagportrait.web.controller.capacity;

import com.ideal.tagportrait.dto.PieChart;
import com.ideal.tagportrait.dto.TreeNode;
import com.ideal.tagportrait.entity.Area;
import com.ideal.tagportrait.framework.web.json.JsonObject;
import com.ideal.tagportrait.service.AreaService;
import com.ideal.tagportrait.service.MainService;
import com.ideal.tagportrait.service.PieService;
import com.ideal.tagportrait.service.QueryService;
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
@RequestMapping(BaseController.PORTAL_PREFIX + "capacity/*")
public class CapacityController {
    protected Logger logger = LoggerFactory.getLogger(CapacityController.class);

    @Resource
    private QueryService queryService;
    @Resource
    private MainService mainService;
    @Resource
    private AreaService areaService;
    @RequestMapping("index")
    public void index(Model model) {
        List<Area> names1 = areaService.findAll();
        model.addAttribute("areaList", names1);
    }
    @RequestMapping("tag_tree")
    @ResponseBody
    public List<TreeNode> showTagTree(String id, Long areaId) {
        logger.info(String.format("id:%s", id));
        logger.info(String.format("areaId:%s", areaId));
        List<TreeNode> treeNodeList = mainService.getTagTree(id, areaId);
        return treeNodeList;
    }

    @RequestMapping("query")
    @ResponseBody
    public JsonObject query(String filter,String city,int resultType) {
        return JsonObject.success(queryService.query(filter,city,resultType));
    }


}
