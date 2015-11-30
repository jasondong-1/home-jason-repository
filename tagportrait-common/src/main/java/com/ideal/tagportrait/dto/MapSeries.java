package com.ideal.tagportrait.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public class MapSeries implements Serializable {
    private String name;
    private String type;
    private String mapType;
    private List<MapData> data;

    public MapSeries() {
    }

    public MapSeries(String name, String type, String mapType) {
        this.name = name;
        this.type = type;
        this.mapType = mapType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public List<MapData> getData() {
        return data;
    }

    public void setData(List<MapData> data) {
        this.data = data;
    }
}
