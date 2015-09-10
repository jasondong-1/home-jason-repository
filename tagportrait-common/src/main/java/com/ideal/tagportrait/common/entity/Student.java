package com.ideal.tagportrait.common.entity;

import com.ideal.tagportrait.common.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yuanyuntao
 * @mail 18918567223@189.cn
 * created on 2015/09/10 10:31
 */
@Entity
@Table(name = "tb_student")
public class Student extends AutoModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
