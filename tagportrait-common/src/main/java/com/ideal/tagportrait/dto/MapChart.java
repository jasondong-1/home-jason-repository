package com.ideal.tagportrait.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public class MapChart implements Serializable{
    private List<MapSeries> series;

    public List<MapSeries> getSeries() {
        return series;
    }

    public void setSeries(List<MapSeries> series) {
        this.series = series;
    }
}
