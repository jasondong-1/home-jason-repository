package com.ideal.tagportrait.web.framework.web.message;

/**
 * User: 邢森
 * Email: xingsen@join-cn.com
 */
public enum WebMessageLevel {
    INFO("提示"), SUCCESS("成功"), ERROR("错误"), WARN("警告");
    private String text;

    WebMessageLevel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
