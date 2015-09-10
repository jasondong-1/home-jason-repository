package com.ideal.tagportrait.common.entity.security;

import com.ideal.tagportrait.common.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Entity
@Table(name = "sec_menu")
public class Menu extends AutoModel {
    private String title;
    private String url;
    private String cssClass;

    private Long parentId;

    @Transient
    private List<Menu> subMenu;

    @Transient
    private String requestUri;

    public Menu() {
        super();
    }

    public Menu(Long id) {
        super(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public boolean getIsActive() {
        //for jstl 1
        if (requestUri == null || requestUri.equals("")
                || url == null || url.equals("")) {
            return false;
        }
        int lastRequest = requestUri.lastIndexOf("/");
        String request = requestUri.substring(0, lastRequest);
        int lastSelf = url.lastIndexOf("/");
        String self = lastSelf > 0 ? url.substring(0, lastSelf) : url;
        return request.equals(self);
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List subMenu) {
        this.subMenu = subMenu;
    }
}
