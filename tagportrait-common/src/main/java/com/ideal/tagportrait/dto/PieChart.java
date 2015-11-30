package com.ideal.tagportrait.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/24 17:15
 */

public class PieChart implements Serializable{
    private List<SeriesPie> series;

    public List<SeriesPie> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesPie> series) {
        this.series = series;
    }
}
