package com.ideal.tagportrait.dto;

import java.io.Serializable;


/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/24 17:17
 */

public class PieData implements Serializable {

    private Long value;
    private String name;
    public PieData(){}

    public  PieData(Long value,String name){
        this.value = value;
        this.name = name;
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
}
