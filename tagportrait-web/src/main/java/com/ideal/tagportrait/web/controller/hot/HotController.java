package com.ideal.tagportrait.web.controller.hot;

import com.ideal.tagportrait.service.HotService;
import com.ideal.tagportrait.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

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
//        /*List<PlatformForm> platformFormList = mainService.getPlatformInfo();
//        model.addAttribute("platforms",platformFormList);*/
        //List<String> names = hotService.findStudentNames();
        //model.addAttribute("hqlList", names);
    }
}
