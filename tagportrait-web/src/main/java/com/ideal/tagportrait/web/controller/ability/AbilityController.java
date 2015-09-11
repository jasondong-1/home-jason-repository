package com.ideal.tagportrait.web.controller.ability;

import com.ideal.tagportrait.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yuanyuntao
 * @email yuanyuntao@shtel.com.cn
 */
@Controller
@RequestMapping(BaseController.PORTAL_PREFIX + "ability/*")
public class AbilityController {
    @RequestMapping("index")
    public void index(Model model) {
        /*List<PlatformForm> platformFormList = mainService.getPlatformInfo();
        model.addAttribute("platforms",platformFormList);*/
        // List<String> names = mainService.findStudentNames();
        //model.addAttribute("hqlList", names);
    }
}
