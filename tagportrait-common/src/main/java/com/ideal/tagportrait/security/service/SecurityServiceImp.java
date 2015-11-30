package com.ideal.tagportrait.security.service;

import com.ideal.tagportrait.entity.security.Menu;
import com.ideal.tagportrait.entity.security.Role;
import com.ideal.tagportrait.entity.security.User;
import com.ideal.tagportrait.entity.security.UserRole;
import com.ideal.tagportrait.security.SecurityService;
import com.ideal.tagportrait.security.ShiroDbRealm;
import com.ideal.tagportrait.security.repository.RoleMenuRepository;
import com.ideal.tagportrait.security.repository.RoleRepository;
import com.ideal.tagportrait.security.repository.UserRepository;
import com.ideal.tagportrait.security.repository.UserRoleRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * 用户管理类.
 */
@Service
@Transactional
public class SecurityServiceImp implements SecurityService {
    private static Logger logger = LoggerFactory.getLogger(SecurityServiceImp.class);
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;
    @Resource
    private UserRoleRepository userRoleRepository;
    @Resource
    private RoleMenuRepository roleMenuRepository;

    @Override
    public User findUserByLoginName(String loginName) {
        User user = userRepository.findByLoginName(loginName);
        return user;
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findOne(id);
        Set<UserRole> roles = user.getRoleSet();
        for (UserRole userRole : roles) {
            Role role = userRole.getRole();
            user.addRole(role.getId(), role.getCnName());
        }
        return user;
    }


    @Override
    public User registerOrUpdateUser(Long id, String loginName, String password, String name, Long[] roleIds) {
        logger.debug("registerUser {} {}", loginName, roleIds);
        List<Role> roles = roleRepository.findByIds(Arrays.asList(roleIds));

        User user = new User();
        if (id != null) {
            user = userRepository.findOne(id);
            userRoleRepository.deleteByUser(id);
        }
        user.setLoginName(loginName);
        user.setName(name);
        entryptPassword(user, password);
        userRepository.save(user);
        for (Role role : roles) {
            UserRole userRole = new UserRole(user, role);
            userRoleRepository.save(userRole);
        }
        return user;
    }

    @Override
    public User changePassword(Long userId, String password) {
        User user = userRepository.findOne(userId);
        // Assert.notNull(user, "用户不存在");
        entryptPassword(user, password);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<Role> getRoleByUser(Long id) {
        List<Role> roles = userRoleRepository.findRoleByUser(id);
        return roles;
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public User getLoginUser() {
        ShiroDbRealm.ShiroUser user = (ShiroDbRealm.ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            return null;
        }
        return this.getUser(user.getId());
    }

    /**
     * 获取菜单树
     * @param requestUri
     * @return
     */
    @Override
    public List<Menu> buildMenu(String requestUri) {
        User user = getLoginUser();
        List<Menu> root = new ArrayList<>();
        List<Menu> parentMenu = roleMenuRepository.findParentMenu(user.getRoleIds());
        for (Menu menu : parentMenu) {
            menu.setRequestUri(requestUri);
            List<Menu> sMenu = roleMenuRepository.findSubMenu(user.getRoleIds(), menu.getId());
            List<Menu> subMenu = new ArrayList<>();
            for (Menu ssMenu : sMenu) {
                ssMenu.setRequestUri(requestUri);
                subMenu.add(ssMenu);
            }
            if (subMenu != null && subMenu.size() > 0) {
                menu.setSubMenu(subMenu);
            }
            root.add(menu);
        }
        return root;
    }

    protected void entryptPassword(User user, String password) {
        if (StringUtils.isNotEmpty(password)) {
            //user.setPassword(DigestUtils.md5Hex(password));
            user.setPassword(password);
        }
    }
}

