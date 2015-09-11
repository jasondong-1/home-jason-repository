package com.ideal.tagportrait.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public class XAxis implements Serializable{
    private String type;
    private List<String> data;

    public XAxis() {
    }

    public XAxis(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
