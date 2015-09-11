package com.ideal.tagportrait.web;

import com.ideal.tagportrait.security.SecurityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * User : xushanshan
 * Email : 1337220620@qq.com
 * Date : 2015-06-18
 * 处理完的请求做统一处理
 * 1.增加菜单
 */
public class BeeUIInterceptors extends UIInterceptors {
    @Resource
    private SecurityService securityService;

    @Override
    public void postHandle0(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (StringUtils.isBlank(request.getHeader("x-requested-with"))) {
            String requestUri = request.getServletPath();
            request.setAttribute("menuList", securityService.buildMenu(requestUri));
        }
    }
}
