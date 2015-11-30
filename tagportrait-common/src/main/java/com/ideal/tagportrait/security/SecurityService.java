package com.ideal.tagportrait.security;

import com.ideal.tagportrait.entity.security.Menu;
import com.ideal.tagportrait.entity.security.Role;
import com.ideal.tagportrait.entity.security.User;

import java.util.List;

/**
 * Email: 1337220620@qq.com
 * User: xushanshan
 */
public interface SecurityService {
    /*用户*/
    public User findUserByLoginName(String loginName);

    public User getUser(Long id);

    public User registerOrUpdateUser(Long id, String loginName, String password, String name, Long[] roleIds);

    public User changePassword(Long userId, String password);

    /*
    public void deleteUser(Long[] ids);

    public Page<User> searchUser(Map<String, Object> searchParams, Pageable pageable);
    */
    /*角色*//*
    public Role saveOrUpdateRole(Long id, String roleName, String cnName, String permissions);

    public List<Role> getAllRole();

    public void deleteRole(Long ids[]);
    */
    public List<Role> getRoleByUser(Long id);

    public Role getRole(Long id);

    public User getLoginUser();

    /*
    public List<Menu> getMenuItem();
    */
    public List<Menu> buildMenu(String requestUri);
    /*
    public void deleteMenuItem(Long id);

    public Menu getMenuItem(Long id);

    public Menu saveMenuItem(Menu menuItem);
    */
}
