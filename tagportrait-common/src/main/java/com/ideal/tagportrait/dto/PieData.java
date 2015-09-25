package com.ideal.tagportrait.dto;

import java.io.Serializable;


/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/24 17:17
 */

public class PieData implements Serializable {
    private String name;
    private Long tagNum;
    public PieData(){}

    public  PieData(Long tagNum,String name){
        this.tagNum = tagNum;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTagNum() {
        return tagNum;
    }

    public void setTagNum(Long tagNum) {
        this.tagNum = tagNum;
    }
}
