package com.ideal.tagportrait.common.entity.security;

import com.ideal.tagportrait.common.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author xushanshan
 * @email 1337220620@qq.com
 */
@Entity
@Table(name = "sec_role")
public class Role extends AutoModel {
    private String name;
    private String cnName;

    public Role() {
        super();
    }

    public Role(Long id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
