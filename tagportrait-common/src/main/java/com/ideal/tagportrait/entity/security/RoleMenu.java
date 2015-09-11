package com.ideal.tagportrait.entity.security;

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
@Table(name = "sec_role_menu")
public class RoleMenu extends AutoModel {
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    public RoleMenu() {
        super();
    }

    public RoleMenu(Long id) {
        super(id);
    }

    public RoleMenu(Role role, Menu menu) {
        this.role = role;
        this.menu = menu;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
