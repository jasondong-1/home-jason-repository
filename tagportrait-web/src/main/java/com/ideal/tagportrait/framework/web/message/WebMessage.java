package com.ideal.tagportrait.framework.web.message;

/**
 * User: 邢森
 * Email: xingsen@join-cn.com
 */
public class WebMessage {
    private WebMessageLevel level;
    private String message;


    public WebMessage(String message, WebMessageLevel level) {
        this.message = message;
        this.level = level;
    }

    public WebMessageLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }
}
