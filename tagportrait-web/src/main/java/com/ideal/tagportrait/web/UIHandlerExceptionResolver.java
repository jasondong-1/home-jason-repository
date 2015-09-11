package com.ideal.tagportrait.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideal.tagportrait.framework.web.json.JsonObject;
import com.ideal.tagportrait.framework.web.message.WebMessageLevel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: xingsen
 * Date: 13-12-27
 * Time: 下午4:34
 */
public class UIHandlerExceptionResolver implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(UIHandlerExceptionResolver.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception ex) {
        logger.info(request == null ? "null" : request.getContextPath());
        //记录异常
        logger.error(ex.getMessage(), ex);
        try {
            //根据http accept决定返回错误页面或包含错误信息的json数据
            String accept = request.getHeader("Accept");
            if (StringUtils.isEmpty(accept)) {
                accept = request.getHeader("accept");
            }
            if (accept.indexOf("json") != -1) {
                handlerJsonException(ex, request, response);
                return null;
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/jsp/error/500.jsp");
        return modelAndView;
    }

    protected void handlerJsonException(Exception ex, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        String message = StringUtils.isEmpty(ex.getMessage()) ? "未知错误" : ex.getMessage();
        mapper.writeValue(response.getWriter(), JsonObject.alert(message, WebMessageLevel.ERROR));
    }
}
