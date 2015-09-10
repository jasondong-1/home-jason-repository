package com.ideal.tagportrait.web.framework.web.json;

import java.io.Serializable;

/**
 * User: 邢森
 * Email: xingsen@join-cn.com
 */
public class JsonUpdater implements Serializable {
    private String id;
    private String html;

    public JsonUpdater(String id, String html) {
        this.id = id;
        this.html = html;
    }


    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
