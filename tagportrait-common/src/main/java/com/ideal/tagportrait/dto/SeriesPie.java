package com.ideal.tagportrait.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/24 17:18
 */

public class SeriesPie  implements Serializable {
    private List<PieData> data;
    private String name;
    public SeriesPie(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PieData> getData() {
        return data;
    }

    public void setData(List<PieData> data) {
        this.data = data;
    }
}
