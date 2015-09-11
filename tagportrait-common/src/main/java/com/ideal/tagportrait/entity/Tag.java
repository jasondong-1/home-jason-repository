package com.ideal.tagportrait.entity;

import com.ideal.tagportrait.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Entity
@Table(name = "tb_tag")
public class Tag extends AutoModel {
    private String name;
    private Long level;
    private Long parentId;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
