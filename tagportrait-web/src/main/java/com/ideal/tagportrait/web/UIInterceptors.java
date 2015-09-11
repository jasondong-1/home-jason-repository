package com.ideal.tagportrait.web;

import com.ideal.tagportrait.framework.web.HttpRequests;
import com.ideal.tagportrait.web.controller.UIController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * User: xingsen
 * Date: 13-10-24
 * Time: 下午3:36
 */
public class UIInterceptors extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(UIInterceptors.class);
    private boolean debug;

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
         /*这里可以针对所有的Controller做一些统一处理 */
        if (handler instanceof HandlerMethod) {
            if (((HandlerMethod) handler).getBean() instanceof UIController) {
                UIController controller = (UIController) ((HandlerMethod) handler).getBean();
                String portalPrefix = controller.getPortalPrefix();
                request.setAttribute("portalPrefix", portalPrefix);
                if (!portalPrefix.startsWith("/") && !portalPrefix.trim().equals("")) {
                    request.setAttribute("portalPrefix", "/" + portalPrefix);
                }
            }
        }

        //排序参数
        Map<String, Object> searchParams = HttpRequests.getParametersStartingWith(request, "Q_");
        request.setAttribute("sortType", request.getParameter("sortType"));
        request.setAttribute("searchParams", HttpRequests.encodeParameterStringWithPrefix(searchParams, "Q_"));

        postHandle0(request, response, handler, modelAndView);
    }

    public void postHandle0(HttpServletRequest request, HttpServletResponse response, Object handler,
                            ModelAndView modelAndView) throws Exception {

    }
}

