package com.ideal.tagportrait.security;

import com.ideal.tagportrait.entity.security.Role;
import com.ideal.tagportrait.entity.security.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author wanghuiren
 * @email wanghuiren@shtel.com.cn
 */
public class ShiroDbRealm extends AuthorizingRealm {
    // @Resource
    // private UserWebservice userWebservice;

    @Resource
    private SecurityService accountService;
    private static final Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//        String loginName = token.getUsername();
//        String passWord = String.valueOf(token.getPassword());
//        Integer result = 0;//userWebservice.listUser(loginName, passWord).getApiCode();
//
//        if (0 == result.intValue()) {
//            logger.debug(loginName);
//            User user = accountService.findUserByLoginName(loginName);
//            if (user != null) {
//                logger.debug("user login success");
//                if (!StringUtils.equals(passWord, user.getPassword())) {
//                    user = accountService.changePassword(user.getId(), passWord);
//                    logger.debug("update user {} password success!", user.getLoginName());
//                }
//                return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName()),
//                        user.getPassword(), getName());
//            } else {
//                user = accountService.registerOrUpdateUser(null, loginName, passWord, loginName, new Long[]{1L});
//                logger.debug("create new user {} and login success!", user.getLoginName());
//                return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName()),
//                        user.getPassword(), getName());
//            }
//        } else {
//            logger.debug("user login failed.");
//            return null;
//        }

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String loginName = token.getUsername();
        String passWord = String.valueOf(token.getPassword());
        User user = accountService.findUserByLoginName(loginName);
        if (user != null && user.getPassword().equals(passWord)) {
            logger.debug("{} user login success", loginName);
            return new SimpleAuthenticationInfo(new ShiroUser(user.getId(), user.getLoginName(), user.getName()),
                    user.getPassword(), getName());
        } else {
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        User user = accountService.findUserByLoginName(shiroUser.getLoginName());
        List<Role> roles = accountService.getRoleByUser(user.getId());
        logger.debug("getRole {}", shiroUser.getLoginName());
        for (Role role : roles) {
            logger.debug(role.getName());
            info.addRole(role.getName());
        }
        return info;
    }


    /**
     * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
     */
    public static class ShiroUser implements Serializable {
        private Long id;
        private String loginName;
        private String name;

        public ShiroUser(Long id, String loginName, String name) {
            this.id = id;
            this.loginName = loginName;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getName() {
            return name;
        }

        /**
         * <shiro:principal/>输出.
         */
        @Override
        public String toString() {
            logger.debug("ShiroUser{" +
                    "id=" + id +
                    ", loginName='" + loginName + '\'' +
                    ", name='" + name + '\'' +
                    '}');
            return loginName;
        }

        /**
         * 重载hashCode,只计算loginName;
         */
        @Override
        public int hashCode() {
            return loginName.hashCode();
        }

        /**
         * 重载equals,只计算loginName;
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ShiroUser other = (ShiroUser) obj;
            if (loginName == null) {
                if (other.loginName != null) {
                    return false;
                }
            } else if (!loginName.equals(other.loginName)) {
                return false;
            }
            return true;
        }
    }
}
