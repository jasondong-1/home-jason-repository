package com.ideal.tagportrait.dto;

import java.io.Serializable;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public class MapData implements Serializable {
    private String name;
    private Long value;
    private Long percent;

    public MapData() {
    }

    public MapData(String name, Long value, Long percent) {
        this.name = name;
        this.value = value;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }
}
