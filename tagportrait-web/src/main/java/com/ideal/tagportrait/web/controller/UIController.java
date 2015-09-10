package com.ideal.tagportrait.web.controller;

import com.ideal.tagportrait.web.framework.web.message.WebMessage;
import com.ideal.tagportrait.web.framework.web.message.WebMessageLevel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * User: 邢森
 * Email: xingsen@join-cn.com
 */
public abstract class UIController {

    public void saveError(Model model, String error) {
        add(model, "global_error", new WebMessage(error, WebMessageLevel.ERROR));
    }

    public void saveMessage(Model model, String message) {
        add(model, "global_message", new WebMessage(message, WebMessageLevel.INFO));
    }

    public void saveSuccess(Model model, String message) {
        add(model, "global_success", new WebMessage(message, WebMessageLevel.SUCCESS));
    }

    protected void add(Model model, String key, WebMessage message) {
        List<WebMessage> list = new ArrayList<WebMessage>();
        if (model.containsAttribute(key)) {
            list = (List<WebMessage>) model.asMap().get(key);
        }
        list.add(message);
        if (model instanceof RedirectAttributes) {
            ((RedirectAttributes) model).addFlashAttribute(key, list);
        } else {
            model.addAttribute(key, list);
        }
    }

    protected void doInitBinder(HttpServletRequest requst, ServletRequestDataBinder binder) {
    }

    public abstract String getPortalPrefix();

    protected String redirect(String path) {
        String portalName = getPortalPrefix();
        if (portalName.startsWith("/")) {
            return "redirect:" + portalName + path;
        }
        return "redirect:/" + portalName + path;
    }

    protected String forward(String path) {
        String portalName = getPortalPrefix();
        if (portalName.startsWith("/")) {
            return "forward:" + portalName + path;
        }
        return "forward:/" + portalName + path;
    }

    protected PageRequest buildPageRequest(HttpServletRequest request) {
        return buildPageRequest(request, 10);
    }

    protected PageRequest buildPageRequest(HttpServletRequest request, int pageSize) {
        int pageno = 0;
        try {
            pageno = Integer.parseInt(request.getParameter("page")) - 1;
        } catch (Exception e) {
            pageno = 0;
        }
        //默认30页
        return new PageRequest(pageno, pageSize);
    }

    protected PageRequest buildPageRequest(HttpServletRequest request, Sort sort) {
        return buildPageRequest(request, 10, sort);
    }

    protected PageRequest buildPageRequest(HttpServletRequest request, int pageSize, Sort sort) {
        int pageno = 0;
        try {
            pageno = Integer.parseInt(request.getParameter("page")) - 1;
        } catch (Exception e) {
            pageno = 0;
        }
        //默认30页
        return new PageRequest(pageno, pageSize, sort);
    }
}
