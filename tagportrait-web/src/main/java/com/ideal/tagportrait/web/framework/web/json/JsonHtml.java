package com.ideal.tagportrait.web.framework.web.json;

import java.io.Serializable;

public class JsonHtml implements Serializable {
    private String html;

    public JsonHtml(String html) {
        this.html = html;
    }


    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
