package com.ideal.tagportrait.web.controller.hot;

import com.ideal.tagportrait.entity.Tag;
import com.ideal.tagportrait.framework.web.json.JsonObject;
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
}
