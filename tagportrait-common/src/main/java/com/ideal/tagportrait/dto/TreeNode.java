package com.ideal.tagportrait.dto;

import java.io.Serializable;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public class TreeNode implements Serializable {
    private String id;
    private String pId;
    private String name;
    private Boolean isParent;

    public TreeNode() {
    }

    public TreeNode(String id, String pId, String name, Boolean isParent) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.isParent = isParent;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }
}
