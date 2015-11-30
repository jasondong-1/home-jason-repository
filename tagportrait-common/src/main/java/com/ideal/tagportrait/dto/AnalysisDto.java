package com.ideal.tagportrait.dto;

/**
 * @author yaotianli
 * @mail 18514733097@189.cn
 * created on 2015/09/14 16:22
 */

public class AnalysisDto {
    private Long id;
    private String name;
    private Float heatValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getHeatValue() {
        return heatValue;
    }

    public void setHeatValue(Float heatValue) {
        this.heatValue = heatValue;
    }

    public AnalysisDto(Long id, String name, Float heatValue) {
        this.id = id;
        this.name = name;
        this.heatValue = heatValue;
    }

    public AnalysisDto(){};
}
