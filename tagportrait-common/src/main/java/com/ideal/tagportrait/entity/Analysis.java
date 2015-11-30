package com.ideal.tagportrait.entity;

import com.ideal.tagportrait.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Entity
@Table(name = "tb_analysis")
public class Analysis extends AutoModel{
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
    private Long tagNum;
    private Long percent;
    private Float heatValue;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Long getTagNum() {
        return tagNum;
    }

    public void setTagNum(Long tagNum) {
        this.tagNum = tagNum;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }

    public Float getHeatValue() {
        return heatValue;
    }

    public void setHeatValue(Float heatValue) {
        this.heatValue = heatValue;
    }
}
