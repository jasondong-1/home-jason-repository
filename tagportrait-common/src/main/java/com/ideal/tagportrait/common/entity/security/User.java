package com.ideal.tagportrait.common.entity.security;

import com.ideal.tagportrait.common.framework.entity.AutoModel;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
@Entity
@Table(name = "sec_user")
public class User extends AutoModel {
    private String loginName;
    private String password;
    private String name;

    @Transient
    private List<String> roleNames = new ArrayList<String>();
    @Transient
    private List<Long> roleIds = new ArrayList<Long>();

    @OneToMany(mappedBy = "user")
    private Set<UserRole> roleSet = new HashSet<UserRole>();

    public User() {
        super();
    }

    public User(Long id) {
        super(id);
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRole> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<UserRole> roleSet) {
        this.roleSet = roleSet;
    }

    public void addRole(long id, String roleName) {
        if (!roleNames.contains(roleName)) {
            this.roleNames.add(roleName);
            this.roleIds.add(id);
        }
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }
}
