package com.ideal.tagportrait.web.controller.main;

import com.google.common.collect.Lists;
import com.ideal.tagportrait.web.controller.BaseController;
import com.ideal.tagportrait.web.service.main.MainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("index")
    public void index(Model model) {
        /*List<PlatformForm> platformFormList = mainService.getPlatformInfo();
        model.addAttribute("platforms",platformFormList);*/
        List<String> names = mainService.findStudentNames();
        model.addAttribute("hqlList", names);
    }

}
