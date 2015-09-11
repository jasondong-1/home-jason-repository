package com.ideal.tagportrait.entity;

import com.ideal.tagportrait.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Entity
@Table(name = "tb_area")
public class Area extends AutoModel{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
